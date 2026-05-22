package org.example.repository;

import org.example.User;
import org.example.repository.impl.GenericRepositoryImpl;

import jakarta.persistence.EntityManager;

public class UserRepository extends GenericRepositoryImpl<User, String> {

    public UserRepository(EntityManager em) {
        super(em, User.class);
    }
}
