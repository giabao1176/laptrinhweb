package com.example.controller.admin;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            String username = req.getParameter("id");
            User user = userService.findById(username); // đã có method này
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/admin/user-form.jsp").forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            String username = req.getParameter("id");
            userService.delete(username);
            resp.sendRedirect("users");
            return;
        }

        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setFullname(req.getParameter("fullname"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        user.setAdmin("on".equals(req.getParameter("admin")));

        userService.save(user); // dùng save() thay cho insert/update
        resp.sendRedirect("users");
    }
}