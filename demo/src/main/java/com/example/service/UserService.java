package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;

public class UserService {
    
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        // Kiểm tra logic nghiệp vụ (nếu cần)
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        
        // Gọi DAO để kiểm tra trong Database
        return userDAO.getUserByUsernamePassword(username, password);
    }
}