package com.example.controller;

import com.example.model.Category;
import com.example.service.CategoryService;
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
@WebServlet("/admin/category/add")
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryService();
    
    // Tên thư mục lưu trữ (nằm trong thư mục deploy của web app)
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/category/add.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String cateName = request.getParameter("cateName");
        
        String fileName = "";
        try {
            Part filePart = request.getPart("icons"); 
            fileName = getFileName(filePart);
            
            if (fileName != null && !fileName.isEmpty()) {
                // 1. Lấy đường dẫn thực tế của ứng dụng trên server
                // Ví dụ kết quả: D:\...\demo\target\demo-1.0-SNAPSHOT\
                String applicationPath = request.getServletContext().getRealPath("");
                
                // Tạo đường dẫn tới thư mục uploads
                String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
                
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // Tạo thư mục nếu chưa có
                }
                
                // Ghi file
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = URLEncoder.encode("Lỗi xử lý file upload: " + e.getMessage(), "UTF-8");
            response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + errorMsg);
            return;
        }
        
        if (cateName != null && !cateName.trim().isEmpty()) {
            Category newCategory = new Category();
            newCategory.setCateName(cateName);
            newCategory.setIcons(fileName); 
            
            if (categoryService.addCategory(newCategory)) {
                String successMsg = URLEncoder.encode("Thêm danh mục thành công!", "UTF-8");
                response.sendRedirect(request.getContextPath() + "/admin/category?alert=" + successMsg);
                return;
            } else {
                request.setAttribute("alert", "Lỗi DB: Thêm danh mục thất bại.");
            }
        } else {
            request.setAttribute("alert", "Tên danh mục không được để trống.");
        }
        
        request.getRequestDispatcher("/category/add.jsp").forward(request, response);
    }

    private String getFileName(final Part part) {
        if (part.getSize() == 0) return null;
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}