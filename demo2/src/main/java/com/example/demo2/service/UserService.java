package com.example.demo2.service;

import com.example.demo2.dao.UserDAO;
import com.example.demo2.model.User;

import java.util.List;  // ← THÊM DÒNG NÀY (lỗi cannot find symbol List)

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) { // plain text hoặc BCrypt tùy bạn
            return user;
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (userDAO.findByUsername(username) != null) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // hoặc BCrypt.hashpw(password, BCrypt.gensalt())
        user.setRoleid(1);
        userDAO.insert(user);
        return true;
    }

    public void update(User user, String newUsername, String newPassword) {
        if (newUsername != null && !newUsername.isEmpty()) {
            user.setUsername(newUsername);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(newPassword); // hoặc BCrypt.hashpw(...)
        }
        userDAO.update(user);
    }

    // ← THÊM HÀM NÀY ĐỂ ChangeRoleController DÙNG ĐƯỢC
    public void changeRole(int userId, int newRole) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setRoleid(newRole);
            userDAO.update(user);
        }
    }

    // ← THÊM HÀM NÀY ĐỂ LIST TẤT CẢ USER
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
}