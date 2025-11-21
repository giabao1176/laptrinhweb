package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/change-role")
public class ChangeRoleController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("auth");
        if (auth == null || auth.getRoleid() != 3) {
            resp.sendError(403, "Chỉ Admin được phép!");
            return;
        }

        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/views/change_role.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("auth");
        if (auth == null || auth.getRoleid() != 3) {
            resp.sendError(403);
            return;
        }

        int userId = Integer.parseInt(req.getParameter("userId"));
        int newRole = Integer.parseInt(req.getParameter("newRole"));

        userService.changeRole(userId, newRole);  // ← BÂY GIỜ ĐÃ CÓ HÀM NÀY
        resp.sendRedirect(req.getContextPath() + "/admin/home");
    }
}