package com.ben.repo;

import com.ben.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepo extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder getPaymentOrdersByPaymentLinkId(String paymentLinkId);
}
