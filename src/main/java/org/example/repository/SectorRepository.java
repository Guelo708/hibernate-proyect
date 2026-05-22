package org.example.repository;

import org.example.Sector;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class SectorRepository extends GenericRepositoryImpl<Sector, Long> {

    public SectorRepository(EntityManager em) {
        super(em, Sector.class);
    }
}
