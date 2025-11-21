package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO(); 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String newPassword = request.getParameter("password");
        String newEmail = request.getParameter("email");
        String newFullname = request.getParameter("fullname");
        
        // Cập nhật User object
        currentUser.setFullname(newFullname);
        currentUser.setEmail(newEmail);
        
        // Nếu người dùng nhập mật khẩu mới, thì cập nhật
        if (newPassword != null && !newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
        }
        
        // Lưu cập nhật vào Database
        if (userDAO.updateUser(currentUser)) {
            // Cập nhật thành công, set lại User object mới vào session
            session.setAttribute("user", currentUser); 
            request.setAttribute("alert", "Cập nhật tài khoản thành công!");
        } else {
            request.setAttribute("alert", "Cập nhật tài khoản thất bại! Vui lòng kiểm tra lại.");
        }

        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }
}