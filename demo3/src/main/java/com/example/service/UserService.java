package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import java.util.List;

public class UserService {
    private final UserDao dao = new UserDao();

    public User login(String username, String password) {
        User user = dao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    // THÊM METHOD NÀY ĐỂ TRÁNH LỖI COMPILE
    public User findById(String username) {
        return dao.findByUsername(username);
    }

    public List<User> findAll() {
        return dao.findAll();
    }

    public void save(User user) {
        dao.save(user);
    }

    public void delete(String username) {
        dao.delete(username);
    }
}