package com.example.demo2.filter;

import com.example.demo2.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Cho phép login, register, static files
        if (uri.equals(contextPath + "/login") || uri.equals(contextPath + "/register") ||
            uri.contains("/category/") || uri.contains("/static/") || uri.equals(contextPath + "/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("auth") : null;

        if (user == null) {
            response.sendRedirect(contextPath + "/login");
            return;
        }

        // Lọc quyền theo path
        String path = uri.substring(contextPath.length());
        if (path.startsWith("/user/") && user.getRoleid() != 1 ||
            path.startsWith("/manager/") && user.getRoleid() != 2 ||
            path.startsWith("/admin/") && user.getRoleid() != 3) {
            response.sendError(403, "Không có quyền");
            return;
        }

        chain.doFilter(request, response);
    }
}