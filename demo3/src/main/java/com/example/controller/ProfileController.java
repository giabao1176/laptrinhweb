package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig(
    fileSizeThreshold = 1024*1024*2,
    maxFileSize = 1024*1024*10,
    maxRequestSize = 1024*1024*50
)
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private UserService service = new UserService();
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        user.setFullname(req.getParameter("fullname"));
        user.setPhone(req.getParameter("phone"));
        user.setEmail(req.getParameter("email"));

        Part part = req.getPart("photo");
        if (part != null && part.getSize() > 0) {
            String fileName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            part.write(uploadPath + File.separator + fileName);
            user.setImages("/" + UPLOAD_DIR + "/" + fileName);
        }

        service.save(user);
        session.setAttribute("currentUser", user);
        resp.sendRedirect("profile");
    }
}