<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ | Shop Bán Hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding-top: 50px;
        }
        .welcome-box {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: 0 auto;
        }
        .btn-logout {
            display: inline-block;
            background-color: #dc3545;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            margin-top: 20px;
            transition: background-color 0.3s;
        }
        .btn-logout:hover {
            background-color: #c82333;
        }
        h2 { color: #007bff; }
    </style>
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user"); 
    %>

    <% if (user != null) { %>
        <div class="welcome-box">
            <h2>Chào mừng, <%= user.getUsername() %>!</h2>
            <p>Đây là trang quản lý/mua sắm của bạn.</p>
            
            <a href="<%= request.getContextPath() %>/logout" class="btn-logout">Đăng xuất</a>
        </div>
        
    <% } else { %>
        
        <% response.sendRedirect(request.getContextPath() + "/login"); %>
        
    <% } %>
</body>
</html>