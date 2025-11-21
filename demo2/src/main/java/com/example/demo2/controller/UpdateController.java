package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// 1. Cập nhật Annotation để nhận diện các đường dẫn mới
@WebServlet({"/update", "/manager/update", "/admin/update", "/user/update"})
public class UpdateController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Luôn set lại rolePath dựa trên user hiện tại để đảm bảo đúng role
        // (Không dùng if null để tránh trường hợp session cũ lưu sai role)
        String rolePath = getRolePath(user.getRoleid());
        req.getSession().setAttribute("rolePath", rolePath);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/update.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String newUsername = req.getParameter("newUsername");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra mật khẩu xác nhận
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu nhập lại không khớp!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/update.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        // Thực hiện update
        userService.update(user, newUsername, newPassword);
        
        // Cập nhật lại user mới vào session
        req.getSession().setAttribute("auth", user);

        // 3. Lấy rolePath để redirect chính xác
        String rolePath = getRolePath(user.getRoleid());
        
        // Redirect về trang home tương ứng với role
        resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
    }

    // Hàm phụ trợ để lấy đường dẫn theo role id (Giúp code gọn hơn và tái sử dụng)
    private String getRolePath(int roleId) {
        if (roleId == 2) {
            return "manager";
        } else if (roleId == 3) {
            return "admin";
        } else {
            return "user";
        }
    }
}