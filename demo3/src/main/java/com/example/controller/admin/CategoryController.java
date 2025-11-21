package com.example.controller.admin;

import com.example.model.Category;
import com.example.model.User;
import com.example.service.CategoryService;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/admin/categories")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final UserService userService = new UserService();
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category cat = categoryService.findById(id);
            req.setAttribute("category", cat);
            req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect("categories");
            return;
        }

        // Chỉ admin thấy tất cả, user thường chỉ thấy của mình
        if (currentUser.isAdmin()) {
            req.setAttribute("categories", categoryService.findAll());
        } else {
            req.setAttribute("categories", categoryService.findByUsername(currentUser.getUsername()));
        }

        req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        Category category = new Category();
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            category.setCategoryId(Integer.parseInt(req.getParameter("id")));
        }

        category.setCategoryname(req.getParameter("categoryname"));
        category.setCategorycode(req.getParameter("categorycode"));
        category.setUser(currentUser);

        // Upload ảnh
        Part part = req.getPart("images");
        if (part != null && part.getSize() > 0) {
            String fileName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            new File(uploadPath).mkdirs();
            part.write(uploadPath + File.separator + fileName);
            category.setImages("/" + UPLOAD_DIR + "/" + fileName);
        }

        categoryService.save(category); // dùng save()
        resp.sendRedirect("categories");
    }
}