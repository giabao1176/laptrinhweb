package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    public void register(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }
    
    public User findById(String username) {
        return userDao.findById(username);
    }
}