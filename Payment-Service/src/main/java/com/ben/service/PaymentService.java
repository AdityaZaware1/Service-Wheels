package com.ben.service;

import com.ben.dto.BookingDto;
import com.ben.dto.UserDto;
import com.ben.entity.PaymentOrder;
import com.ben.enums.PaymentOrderStatus;
import com.ben.reponse.PaymentResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentService {

    public PaymentResponse createPaymentOrder(PaymentOrder paymentOrder,
                                              Long userId,
                                              Long bookingId) throws RazorpayException;

    PaymentOrder getPaymentOrder(Long id);

    PaymentOrder getPaymentOrderByPaymentLinkId(String paymentLinkId);

    PaymentLink createRazorPayPaymentLink(UserDto user, Long amount, Long orderId) throws RazorpayException;

    Boolean updatePaymentOrderStatus(Long orderId, PaymentOrder paymentOrder,
                                     String paymentLinkId, Long booking) throws RazorpayException;
}
