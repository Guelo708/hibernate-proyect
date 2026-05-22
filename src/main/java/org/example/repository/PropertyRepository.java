package org.example.repository;

import org.example.Property;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PropertyRepository extends GenericRepositoryImpl<Property, Long> {

    public PropertyRepository(EntityManager em) {
        super(em, Property.class);
    }
}
