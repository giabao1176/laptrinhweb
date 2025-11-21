<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Danh Mục</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa; }
        .form-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 400px; margin: 0 auto; }
        h2 { color: #007bff; text-align: center; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="file"] { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background-color: #ffc107; color: #333; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-back { background-color: #6c757d; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-left: 10px; }
        .alert { color: red; margin-bottom: 15px; text-align: center; }
        .current-icon { margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>SỬA DANH MỤC</h2>
        
        <% 
            Category category = (Category) request.getAttribute("category");
            String alertMsg = (String) request.getAttribute("alert");
            if (alertMsg != null) {
        %>
                <p class="alert"><%= alertMsg %></p>
        <% 
            }
        %>
        
        <form action="<%= request.getContextPath() %>/admin/category/edit" method="post" enctype="multipart/form-data">
            
            <input type="hidden" name="cateId" value="<%= category.getCateId() %>">
            <input type="hidden" name="oldIcon" value="<%= category.getIcons() != null ? category.getIcons() : "" %>">

            <label>ID:</label>
            <input type="text" value="<%= category.getCateId() %>" disabled>

            <label for="cateName">Tên Danh Mục:</label>
            <input type="text" id="cateName" name="cateName" value="<%= category.getCateName() %>" required>
            
            <label>Icon Hiện Tại:</label>
            <div class="current-icon">
                <% if (category.getIcons() != null && !category.getIcons().isEmpty()) { %>
                    <img src="<%= request.getContextPath() %>/image?fname=<%= category.getIcons() %>" 
                         alt="Icon Hiện Tại" 
                         style="width: 80px; height: 80px; object-fit: cover;">
                <% } else { %>
                    <p>Không có icon</p>
                <% } %>
            </div>
            
            <label for="icons">Icon Mới (Chọn file để thay đổi):</label>
            <input type="file" id="icons" name="icons" accept="image/*">
            
            <button type="submit" class="btn-submit">Cập Nhật</button>
            <a href="<%= request.getContextPath() %>/admin/category" class="btn-back">Hủy</a>
        </form>
    </div>
</body>
</html>