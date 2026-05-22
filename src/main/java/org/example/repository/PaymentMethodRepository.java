package org.example.repository;

import org.example.PaymentMethod;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PaymentMethodRepository extends GenericRepositoryImpl<PaymentMethod, Long> {

    public PaymentMethodRepository(EntityManager em) {
        super(em, PaymentMethod.class);
    }

}
