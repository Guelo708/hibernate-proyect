package org.example.repository;

import org.example.Tariff;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class TariffRepository extends GenericRepositoryImpl<Tariff, Long> {

    public TariffRepository(EntityManager em) {
        super(em, Tariff.class);
    }
}

