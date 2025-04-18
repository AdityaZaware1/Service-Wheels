package com.ben.controller;

import com.ben.dto.BookingDto;
import com.ben.dto.UserDto;
import com.ben.entity.PaymentOrder;
import com.ben.reponse.PaymentResponse;
import com.ben.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/{bookingId}/{userId}")
    public ResponseEntity<PaymentResponse> createPaymentLink(
            @PathVariable Long bookingId,
            @PathVariable Long userId,
            @RequestBody PaymentOrder paymentOrder
            ) throws RazorpayException {

        PaymentResponse paymentResponse = paymentService.createPaymentOrder(paymentOrder, userId, bookingId);
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<PaymentOrder> getPaymentOrder(@PathVariable Long id) {
        PaymentOrder paymentOrder = paymentService.getPaymentOrder(id);
        return ResponseEntity.ok(paymentOrder);
    }

    @GetMapping("/order/{paymentLinkId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderByPaymentLinkId(@PathVariable String paymentLinkId) {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentLinkId(paymentLinkId);
        return ResponseEntity.ok(paymentOrder);
    }


    @GetMapping("/update/{orderId}/{paymentLinkId}/{booking}")
    public ResponseEntity<Boolean> updatePaymentOrderStatus(@PathVariable Long orderId,
                                                            @RequestBody PaymentOrder paymentOrder,
                                                            @PathVariable String paymentLinkId,
                                                            @PathVariable Long booking) throws RazorpayException {

        return ResponseEntity.ok(paymentService.updatePaymentOrderStatus(orderId, paymentOrder, paymentLinkId, booking));
    }

}
