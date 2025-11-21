package com.example.controller;

import com.example.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO(); // Tái sử dụng UserDAO

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        
        String alertMsg = "";

        try {
            // Kiểm tra trùng lặp
            if (userDAO.getUserByUsernamePassword(username, "dummy") != null) { // Kiểm tra username
                alertMsg = "Tài khoản đã tồn tại!";
            } else if (userDAO.checkEmailExists(email)) { // Kiểm tra email
                alertMsg = "Email đã được sử dụng!";
            } else {
                // Thêm người dùng mới vào DB (Cần thêm phương thức register vào UserDAO!)
                if (userDAO.registerUser(username, password, email, fullname)) {
                    alertMsg = "Đăng ký thành công! Vui lòng đăng nhập.";
                    request.setAttribute("alert", alertMsg);
                    response.sendRedirect(request.getContextPath() + "/login"); // Chuyển hướng đến Login
                    return;
                } else {
                    alertMsg = "Đăng ký thất bại. Vui lòng kiểm tra lại thông tin.";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            alertMsg = "Lỗi hệ thống: Không thể kết nối DB.";
        }

        request.setAttribute("alert", alertMsg);
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}