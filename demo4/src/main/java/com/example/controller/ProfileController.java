package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra đăng nhập từ Session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        // Nếu chưa đăng nhập -> đá về trang login
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Nếu đã đăng nhập, forward sang trang profile.jsp để hiển thị form
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        // Bảo mật: Nếu session mất, không cho update
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 1. Lấy dữ liệu text từ form
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // Cập nhật vào object User
        user.setFullname(fullname);
        user.setPhone(phone);

        // 2. Xử lý Upload file ảnh
        try {
            Part part = req.getPart("image"); // 'image' là name của input file trong JSP
            
            // Kiểm tra xem người dùng có chọn file không
            if (part != null && part.getSize() > 0) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                
                // Chỉ xử lý nếu có tên file
                if (filename != null && !filename.isEmpty()) {
                    // Định nghĩa đường dẫn lưu file (folder 'uploads' trong server)
                    String uploadPath = req.getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    
                    // Tạo thư mục nếu chưa tồn tại
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    
                    // Ghi file vào ổ cứng server
                    part.write(uploadPath + File.separator + filename);
                    
                    // Cập nhật tên ảnh vào database user
                    user.setImages(filename);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Có thể thêm thông báo lỗi upload nếu cần
        }

        // 3. Gọi Service để update vào Database SQL Server
        userService.update(user);

        // 4. Cập nhật lại Session để giao diện hiển thị thông tin mới ngay lập tức
        session.setAttribute("account", user);

        // 5. Redirect lại trang profile để tránh resubmit form (F5)
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}