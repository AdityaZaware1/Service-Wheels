package com.ben.service.impl;

import com.ben.dto.BookingDto;
import com.ben.dto.UserDto;
import com.ben.entity.PaymentOrder;
import com.ben.enums.PaymentOrderStatus;
import com.ben.external.BookingService;
import com.ben.external.UserService;
import com.ben.kafka.KafkaProducer;
import com.ben.repo.PaymentOrderRepo;
import com.ben.reponse.PaymentResponse;
import com.ben.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepo paymentOrderRepo;

    private final BookingService bookingService;
    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    private String razorPayKey;

    private String razorPaySecret;

    @Override
    public PaymentResponse createPaymentOrder(PaymentOrder paymentOrder, Long userId, Long bookingId) throws RazorpayException {

        BookingDto bookingDto = bookingService.getBooking(bookingId);

        UserDto userDto = userService.getUserById(userId);

        Long amount = (long) bookingDto.getTotalCost();

        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setUserId(userDto.getId());
        order.setShopId(bookingDto.getShopId());
        order.setBookingId(bookingDto.getId());
        order.setStatus(PaymentOrderStatus.PENDING);

        PaymentOrder savedOrder = paymentOrderRepo.save(order);

        PaymentResponse paymentResponse = new PaymentResponse();

        PaymentLink paymentLink = createRazorPayPaymentLink(
                userDto, savedOrder.getAmount(), savedOrder.getId());

        String paymentLinkUrl = paymentLink.get("short_url");
        String paymentUrlId = paymentLink.get("id");
        paymentResponse.setPaymentLinkUrl(paymentLinkUrl);
        paymentResponse.setGetPaymentLinkId(paymentUrlId);

        savedOrder.setPaymentLinkId(paymentUrlId);
        paymentOrderRepo.save(savedOrder);

        return paymentResponse;

    }

    @Override
    public PaymentOrder getPaymentOrder(Long id) {
        PaymentOrder paymentOrder = paymentOrderRepo.findById(id).get();

        if (paymentOrder == null) {
            throw new RuntimeException("Payment order not found");
        }

        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentLinkId(String paymentLinkId) {
        return paymentOrderRepo.getPaymentOrdersByPaymentLinkId(paymentLinkId);
    }

    @Override
    public PaymentLink createRazorPayPaymentLink(UserDto user, Long amount, Long orderId) throws RazorpayException {

        Long Amount = amount * 100;


        RazorpayClient razorpayClient = new RazorpayClient(razorPayKey, razorPaySecret);

        JSONObject paymentLinkRequest  = new JSONObject();

        paymentLinkRequest.put("amount", Amount);
        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getUsername());
        customer.put("email", user.getEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", user.getEmail());
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url", "http://localhost:8086/api/payment/success"+orderId);
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

        return paymentLink;

    }

    @Override
    public Boolean updatePaymentOrderStatus(Long orderId, PaymentOrder paymentOrder, String paymentLinkId, Long booking) throws RazorpayException {

        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            RazorpayClient razorpayClient = new RazorpayClient(razorPayKey, razorPaySecret);

            Payment payment = razorpayClient.payments.fetch(paymentLinkId);
            Integer amount = payment.get("amount");
            String status = payment.get("status");

            if(status.equals("captured")) {
                // produce kafka event

                kafkaProducer.sendMessage(booking.toString());
                paymentOrder.setStatus(PaymentOrderStatus.COMPLETED);
                paymentOrderRepo.save(paymentOrder);

                return true;
            }
        }
        return false;
    }
}
