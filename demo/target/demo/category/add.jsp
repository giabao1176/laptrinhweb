<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Danh Mục Mới</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa; }
        .form-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 400px; margin: 0 auto; }
        h2 { color: #007bff; text-align: center; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="file"] { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-back { background-color: #6c757d; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-left: 10px; }
        .alert { color: red; margin-bottom: 15px; text-align: center; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>THÊM DANH MỤC MỚI</h2>
        
        <% 
            String alertMsg = (String) request.getAttribute("alert");
            if (alertMsg != null) {
        %>
                <p class="alert"><%= alertMsg %></p>
        <% 
            }
        %>
        
        <form action="<%= request.getContextPath() %>/admin/category/add" method="post" enctype="multipart/form-data">
            <label for="cateName">Tên Danh Mục:</label>
            <input type="text" id="cateName" name="cateName" required>
            
            <label for="icons">Icon/Ảnh Đại Diện:</label>
            <input type="file" id="icons" name="icons" accept="image/*" required>
            
            <button type="submit" class="btn-submit">Thêm Mới</button>
            <a href="<%= request.getContextPath() %>/admin/category" class="btn-back">Quay lại</a>
        </form>
    </div>
</body>
</html>