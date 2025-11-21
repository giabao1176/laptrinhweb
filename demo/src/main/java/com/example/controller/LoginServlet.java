package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = userService.login(username, password);

        if (user != null) {
            // ĐĂNG NHẬP THÀNH CÔNG: Chuyển hướng đến trang quản lý danh mục
            request.getSession().setAttribute("user", user);
            // CHỈNH SỬA Ở ĐÂY: Chuyển hướng đến Controller liệt kê danh mục
            response.sendRedirect(request.getContextPath() + "/admin/category"); 
        } else {
            // ĐĂNG NHẬP THẤT BẠI: Quay lại trang login
            request.setAttribute("alert", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khi người dùng gõ /login, chuyển đến trang login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}