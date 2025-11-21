package com.example.dao;

import com.example.config.JpaConfig;
import com.example.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDao {

    public void insert(User user) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(User user) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public User findById(String username) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(User.class, username);
        } finally {
            em.close();
        }
    }
    
    // Kiểm tra đăng nhập (Tìm user và so sánh password)
    public User checkLogin(String username, String password) {
        User user = findById(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}