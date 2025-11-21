package com.example.demo2;

import com.example.demo2.config.JpaConfig;
import com.example.demo2.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class CreateUsers {
    public static void main(String[] args) {
        var em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();

            User u1 = new User(); u1.setUsername("user1");    u1.setPassword(BCrypt.hashpw("123", BCrypt.gensalt())); u1.setRoleid(1);
            User u2 = new User(); u2.setUsername("manager1"); u2.setPassword(BCrypt.hashpw("123", BCrypt.gensalt())); u2.setRoleid(2);
            User u3 = new User(); u3.setUsername("admin1");   u3.setPassword(BCrypt.hashpw("123", BCrypt.gensalt())); u3.setRoleid(3);

            em.persist(u1); em.persist(u2); em.persist(u3);
            em.getTransaction().commit();
            System.out.println("TẠO 3 USER THÀNH CÔNG! Pass: 123");
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}