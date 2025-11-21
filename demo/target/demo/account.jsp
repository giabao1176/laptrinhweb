<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Tài Khoản</title>
    <style>
        /* Tái sử dụng CSS để đồng bộ */
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa; }
        .form-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 450px; margin: 0 auto; }
        h2 { color: #007bff; text-align: center; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="password"], input[type="email"] { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-back { background-color: #6c757d; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-left: 10px; }
        .alert-success { color: green; margin-bottom: 15px; text-align: center; }
        .alert-error { color: red; margin-bottom: 15px; text-align: center; }
    </style>
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user"); 
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    %>
    <div class="form-container">
        <h2>CẬP NHẬT TÀI KHOẢN</h2>
        
        <% 
            String alertMsg = (String) request.getAttribute("alert");
            if (alertMsg != null) {
        %>
                <p class="<%= alertMsg.contains("thành công") ? "alert-success" : "alert-error" %>"><%= alertMsg %></p>
        <% 
            }
        %>
        
        <form action="<%= request.getContextPath() %>/account" method="post">
            <label for="username">Tên đăng nhập (Không thể thay đổi):</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" disabled>
            
            <label for="fullname">Họ và Tên:</label>
            <input type="text" id="fullname" name="fullname" value="<%= user.getFullname() %>">

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>">
            
            <label for="password">Mật khẩu mới (Để trống nếu không đổi):</label>
            <input type="password" id="password" name="password" placeholder="Nhập mật khẩu mới">
            
            <button type="submit" class="btn-submit">Cập Nhật</button>
            <a href="<%= request.getContextPath() %>/admin/category" class="btn-back">Quay về Trang Quản lý</a>
        </form>
    </div>
</body>
</html>