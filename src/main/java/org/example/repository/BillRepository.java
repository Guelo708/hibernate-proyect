package org.example.repository;

import org.example.Bill;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class BillRepository extends GenericRepositoryImpl<Bill, Long> {

    public BillRepository(EntityManager em) {
        super(em, Bill.class);
    }
}
