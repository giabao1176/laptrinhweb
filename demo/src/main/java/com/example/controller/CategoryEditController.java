package com.example.controller;

import com.example.model.Category;
import com.example.dao.CategoryDAO; 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder; 

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10, 
                 maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/admin/category/edit")
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO = new CategoryDAO();
    private static final String UPLOAD_DIRECTORY = "uploads";

    private String getFileName(final Part part) {
        if (part.getSize() == 0) return null; 
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try {
            int cateId = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDAO.findById(cateId);
            if (category != null) {
                request.setAttribute("category", category);
                request.getRequestDispatcher("/category/edit.jsp").forward(request, response);
            } else {
                String errorMsg = URLEncoder.encode("ID danh mục không tồn tại.", "UTF-8");
                response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + errorMsg);
            }
        } catch (NumberFormatException e) {
            String errorMsg = URLEncoder.encode("ID không hợp lệ.", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + errorMsg);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int cateId;
        try {
            cateId = Integer.parseInt(request.getParameter("cateId"));
        } catch (NumberFormatException e) {
            String errorMsg = URLEncoder.encode("Lỗi: ID không hợp lệ.", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + errorMsg);
            return;
        }

        String cateName = request.getParameter("cateName");
        String oldIcon = request.getParameter("oldIcon"); 
        String newIconName = oldIcon; 

        try {
            Part filePart = request.getPart("icons"); 
            String uploadedFileName = getFileName(filePart);
            
            if (uploadedFileName != null) {
                // 1. Lấy đường dẫn thực tế
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
                
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                
                // 2. Ghi file
                String filePath = uploadPath + File.separator + uploadedFileName;
                filePart.write(filePath);
                
                newIconName = uploadedFileName; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = URLEncoder.encode("Lỗi xử lý file upload: " + e.getMessage(), "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + errorMsg);
            return;
        }

        Category updatedCategory = new Category(cateId, cateName, newIconName);
        if (categoryDAO.update(updatedCategory)) {
            String successMsg = URLEncoder.encode("Cập nhật danh mục thành công!", "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + successMsg);
        } else {
            request.setAttribute("alert", "Cập nhật danh mục thất bại (Lỗi DB).");
            request.setAttribute("category", updatedCategory);
            request.getRequestDispatcher("/category/edit.jsp").forward(request, response);
        }
    }
}