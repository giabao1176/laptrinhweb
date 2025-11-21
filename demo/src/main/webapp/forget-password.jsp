<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
</head>
<body>
    <h2>Đặt lại Mật khẩu</h2>
    <% 
        String alertMsg = (String) request.getAttribute("alert");
        if (alertMsg != null) {
    %>
            <p style="color: blue;"><%= alertMsg %></p>
    <% 
        }
    %>
    <form action="forgot-password" method="post">
        <label for="email">Nhập Email đã đăng ký:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        
        <input type="submit" value="Đặt lại Mật khẩu">
    </form>
    
    <p><a href="<%= request.getContextPath() %>/login">Quay lại Đăng nhập</a></p>
</body>
</html>