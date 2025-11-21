package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.service.CategoryService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet({"/user/home", "/manager/home", "/admin/home"})
public class HomeController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("categories", categoryService.getCategories(user));
        String view = switch (user.getRoleid()) {
            case 1 -> "/views/user/home.jsp";
            case 2 -> "/views/manager/home.jsp";
            case 3 -> "/views/admin/home.jsp";
            default -> "/views/login.jsp";
        };
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }
}