package com.example.controller;

import com.example.model.Category;
import com.example.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class CategoryListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Bắt buộc phải kiểm tra đăng nhập trước khi hiển thị trang quản trị
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Lấy danh sách và chuyển tiếp đến trang View
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("/category/list.jsp").forward(request, response);
    }
}