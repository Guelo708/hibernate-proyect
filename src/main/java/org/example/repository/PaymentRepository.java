package org.example.repository;

import org.example.Payment;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PaymentRepository extends GenericRepositoryImpl<Payment, Long> {

    public PaymentRepository(EntityManager em) {
        super(em, Payment.class);
    }

}
