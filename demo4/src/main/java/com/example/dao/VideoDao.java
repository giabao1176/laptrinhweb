package com.example.dao;

import com.example.config.JpaConfig;
import com.example.model.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideoDao {
    public void insert(Video video) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Video> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }
    
    public List<Video> findByTitle(String title) {
         EntityManager em = JpaConfig.getEntityManager();
         String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
         TypedQuery<Video> query = em.createQuery(jpql, Video.class);
         query.setParameter("title", "%" + title + "%");
         return query.getResultList();
    }
    
    // Các hàm update, delete, findById tương tự...
}