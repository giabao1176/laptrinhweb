package com.huy.config;

import com.huy.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String uri = request.getRequestURI();

        // 1. Kiểm tra đăng nhập (Bắt buộc với mọi trang nằm trong vùng bảo vệ)
        if (user == null) {
            session.setAttribute("error", "Vui lòng đăng nhập!");
            response.sendRedirect("/login");
            return false;
        }

        // 2. Kiểm tra quyền Admin
        // Nếu đường dẫn bắt đầu bằng /admin/... mà user KHÔNG PHẢI là admin
        if (uri.startsWith("/admin") && !Boolean.TRUE.equals(user.getAdmin())) {
            session.setAttribute("error", "Bạn không có quyền truy cập trang quản trị!");
            response.sendRedirect("/login"); // Yêu cầu của bạn: Quay về login
            return false;
        }

        return true; // Cho phép đi tiếp
    }
}