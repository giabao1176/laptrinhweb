package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("logout")) {
            HttpSession session = req.getSession();
            session.removeAttribute("account");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        User user = userService.login(u, p);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", user);
            if (user.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/admin/video");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("message", "Invalid Username or Password");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}