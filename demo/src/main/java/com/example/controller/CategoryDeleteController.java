package com.example.controller;

import com.example.dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO = new CategoryDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra đăng nhập
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String cateIdParam = request.getParameter("id");
        String alertMsg = "";
        
        if (cateIdParam != null && !cateIdParam.isEmpty()) {
            try {
                int cateId = Integer.parseInt(cateIdParam);
                if (categoryDAO.delete(cateId)) {
                    alertMsg = "Xóa danh mục thành công!";
                } else {
                    alertMsg = "Xóa danh mục thất bại (ID không tồn tại hoặc lỗi DB)!";
                }
            } catch (NumberFormatException e) {
                alertMsg = "ID không hợp lệ!";
            }
        } else {
            alertMsg = "Thiếu ID danh mục để xóa.";
        }
        
        // Chuyển hướng về trang danh sách kèm thông báo
        response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + java.net.URLEncoder.encode(alertMsg, "UTF-8"));
    }
}