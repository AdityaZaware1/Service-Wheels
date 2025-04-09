package com.ben.repo;

import com.ben.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepo extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findPaymentLinkId(String paymentLinkId);
}
