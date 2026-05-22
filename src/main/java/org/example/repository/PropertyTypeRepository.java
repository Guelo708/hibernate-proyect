package org.example.repository;

import org.example.PropertyType;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class PropertyTypeRepository extends GenericRepositoryImpl<PropertyType, Long> {

    public PropertyTypeRepository(EntityManager em) {
        super(em, PropertyType.class);
    }

}
