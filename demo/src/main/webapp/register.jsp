<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký Tài Khoản | Shop Bán Hàng</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <style>
        /* Sử dụng lại CSS từ login.jsp cho container và body */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 350px;
        }
        h2 { text-align: center; color: #333; margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc;
            border-radius: 4px; box-sizing: border-box;
        }
        .btn-submit {
            background-color: #28a745; /* Màu xanh lá cho Đăng ký */
            color: white; padding: 10px 15px; border: none; border-radius: 4px;
            cursor: pointer; width: 100%; font-size: 16px; transition: background-color 0.3s;
        }
        .btn-submit:hover { background-color: #1e7e34; }
        .footer-links { text-align: center; margin-top: 15px; font-size: 14px; }
        .footer-links a { color: #007bff; text-decoration: none; margin: 0 8px; }
        .alert { color: red; text-align: center; margin-bottom: 15px; border: 1px solid #ffdddd; background-color: #ffeeee; padding: 10px; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>TẠO TÀI KHOẢN MỚI</h2>
        
        <% 
            String alertMsg = (String) request.getAttribute("alert");
            if (alertMsg != null) {
        %>
                <p class="alert"><%= alertMsg %></p>
        <% 
            }
        %>

        <form action="register" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="fullname">Họ và Tên:</label>
            <input type="text" id="fullname" name="fullname">
            
            <input type="submit" class="btn-submit" value="ĐĂNG KÝ">
        </form>

        <div class="footer-links">
            <a href="<%= request.getContextPath() %>/login">Quay lại Đăng nhập</a>
        </div>
    </div>
</body>
</html>