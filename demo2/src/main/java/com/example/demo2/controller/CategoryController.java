package com.example.demo2.controller;

import com.example.demo2.model.Category;
import com.example.demo2.model.User;
import com.example.demo2.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({"/category/add", "/category/edit", "/category/view", "/category/delete", 
             "/manager/category/add", "/manager/category/edit", "/manager/category/view", "/manager/category/delete",
             "/admin/category/add", "/admin/category/edit", "/admin/category/view", "/admin/category/delete", "/user/category/view"}) // ← SỬA: MAP THÊM /manager/* và /admin/*

public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getServletPath();
        if (action.contains("add")) {
            req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
        } else if (action.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.getCategory(user, id);
            if (category != null) {
                req.setAttribute("category", category);
                req.getRequestDispatcher("/views/category/edit.jsp").forward(req, resp);
            } else {
                resp.sendError(403, "Không có quyền");
            }
        } else if (action.contains("view")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.getCategory(user, id);
            if (category != null) {
                req.setAttribute("category", category);
                req.getRequestDispatcher("/views/category/view.jsp").forward(req, resp);
            } else {
                resp.sendError(403, "Không có quyền");
            }
        } else if (action.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.deleteCategory(user, id);
            String rolePath = switch (user.getRoleid()) {
                case 1 -> "user";
                case 2 -> "manager";
                case 3 -> "admin";
                default -> "";
            };
            resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("auth");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Category category = new Category();
        category.setCate_name(req.getParameter("cate_name"));
        category.setIcons(req.getParameter("icons"));

        if (req.getServletPath().contains("add")) {
            categoryService.addCategory(user, category);
        } else if (req.getServletPath().contains("edit")) {
            category.setCate_id(Integer.parseInt(req.getParameter("cate_id")));
            categoryService.updateCategory(user, category);
        }

        String rolePath = switch (user.getRoleid()) {
            case 1 -> "user";
            case 2 -> "manager";
            case 3 -> "admin";
            default -> "";
        };
        resp.sendRedirect(req.getContextPath() + "/" + rolePath + "/home");
    }
}