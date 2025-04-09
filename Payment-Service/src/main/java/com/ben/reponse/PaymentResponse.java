package com.ben.reponse;

import lombok.Data;

@Data
public class PaymentResponse {

    private String paymentLinkUrl;
    private String getPaymentLinkId;
}
