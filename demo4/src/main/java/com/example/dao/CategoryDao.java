package com.example.dao;

import com.example.config.JpaConfig;
import com.example.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CategoryDao {

    public void insert(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(int categoryId) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Category category = em.find(Category.class, categoryId);
            if (category != null) {
                em.remove(category);
            } else {
                throw new Exception("Not found");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Category findById(int categoryId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Category.class, categoryId);
        } finally {
            em.close();
        }
    }

    public List<Category> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Category> findByName(String keyword) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.categoryname LIKE :key";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("key", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}