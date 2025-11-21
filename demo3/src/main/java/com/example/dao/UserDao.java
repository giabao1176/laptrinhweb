package com.example.dao;

import com.example.config.JpaConfig;
import com.example.model.User;
import jakarta.persistence.*;

import java.util.List;

public class UserDao {

    public User findByUsername(String username) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<User> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createNamedQuery("User.findAll", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void save(User user) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            if (em.find(User.class, user.getUsername()) == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String username) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}