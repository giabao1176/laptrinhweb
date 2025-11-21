package com.example.controller;

import com.example.dao.UserDAO;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID; // Dùng để tạo mật khẩu ngẫu nhiên

@WebServlet("/forgot-password")
public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();
    
    // Tạo mật khẩu ngẫu nhiên đơn giản
    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // Lấy 8 ký tự đầu
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String alertMsg = "";
        
        // 1. Kiểm tra Email có tồn tại không
        if (!userDAO.checkEmailExists(email)) {
            alertMsg = "Email này không tồn tại trong hệ thống.";
        } else {
            // 2. Tạo mật khẩu mới ngẫu nhiên
            String newPassword = generateRandomPassword();
            
            // 3. Cập nhật mật khẩu trong Database
            boolean isSuccess = userDAO.updatePasswordByEmail(email, newPassword);

            if (isSuccess) {
                // 4. (Đáng lẽ phải gửi email ở đây)
                // Giả định gửi email thành công:
                alertMsg = "Mật khẩu mới đã được tạo thành công và là: " + newPassword + ". Vui lòng đăng nhập!"; 
                // LƯU Ý: TRONG THỰC TẾ, KHÔNG BAO GIỜ HIỂN THỊ MẬT KHẨU Ở ĐÂY! Mật khẩu phải được gửi qua Email.
            } else {
                alertMsg = "Có lỗi xảy ra khi cập nhật mật khẩu. Vui lòng thử lại.";
            }
        }
        
        request.setAttribute("alert", alertMsg);
        request.getRequestDispatcher("/forget-password.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/forget-password.jsp").forward(request, response);
    }
}