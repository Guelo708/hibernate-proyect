package org.example.repository;

import org.example.BillStatus;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class BillStatusRepository extends GenericRepositoryImpl<BillStatus, Long> {

    public BillStatusRepository(EntityManager em) {
        super(em, BillStatus.class);
    }
}
