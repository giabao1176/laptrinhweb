package com.example.demo2.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo2PU");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}