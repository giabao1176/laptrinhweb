package com.example.controller.admin;

import com.example.model.Category;
import com.example.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = {"/admin/category", "/admin/category/create", "/admin/category/update", "/admin/category/delete", "/admin/category/edit"})
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        } else {
            List<Category> list = categoryService.findAll();
            req.setAttribute("categories", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("create")) {
            insert(req, resp);
        } else if (url.contains("update")) {
            update(req, resp);
        }
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Category category = new Category();
        category.setCategoryname(req.getParameter("categoryname"));
        category.setCategorycode(req.getParameter("categorycode"));
        category.setStatus(req.getParameter("status") != null);
        
        // Upload ảnh
        Part part = req.getPart("images");
        String fname = getFileName(part);
        if (fname != null && !fname.isEmpty()) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            part.write(uploadPath + File.separator + fname);
            category.setImages(fname);
        } else {
            category.setImages("no-image.png");
        }

        categoryService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.findById(id);
        
        category.setCategoryname(req.getParameter("categoryname"));
        category.setCategorycode(req.getParameter("categorycode"));
        category.setStatus(req.getParameter("status") != null);

        Part part = req.getPart("images");
        String fname = getFileName(part);
        if (fname != null && !fname.isEmpty()) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            part.write(uploadPath + File.separator + fname);
            category.setImages(fname);
        }
        // Nếu không chọn ảnh mới thì giữ nguyên ảnh cũ (JPA merge sẽ xử lý nếu object đã load)

        categoryService.update(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "";
    }
}