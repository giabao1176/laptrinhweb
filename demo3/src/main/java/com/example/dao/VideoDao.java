package com.example.dao;

import com.example.config.JpaConfig;
import com.example.model.Video;
import jakarta.persistence.*;

import java.util.List;

public class VideoDao {

    public List<Video> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createNamedQuery("Video.findAll", Video.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Video findById(String videoId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Video.class, videoId);
        } finally {
            em.close();
        }
    }

    public List<Video> searchByTitle(String keyword) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.findByTitle", Video.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Video> findByCategory(int categoryId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.category.categoryId = :categoryId";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setParameter("categoryId", categoryId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void save(Video video) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            if (em.find(Video.class, video.getVideoId()) == null) {
                em.persist(video);
            } else {
                em.merge(video);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String videoId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Video video = em.find(Video.class, videoId);
            if (video != null) {
                em.remove(video);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void increaseViews(String videoId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Video video = em.find(Video.class, videoId);
            if (video != null) {
                video.setViews(video.getViews() + 1);
                em.merge(video);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}