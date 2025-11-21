<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Danh Mục</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f8f9fa; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        h2 { color: #007bff; margin: 0; }
        
        /* CONTAINER NHÓM NÚT QUẢN LÝ VÀ ĐĂNG XUẤT */
        .header-actions {
            display: flex;
            gap: 10px; /* Tạo khoảng cách giữa các nút */
            align-items: center;
        }

        .btn-logout { 
            background-color: #dc3545; 
            color: white; 
            padding: 8px 15px; 
            text-decoration: none; 
            border-radius: 5px; 
        }
        
        .btn-account {
            background-color: #17a2b8; 
            color: white; 
            padding: 8px 15px; 
            text-decoration: none; 
            border-radius: 5px; 
        }
        
        table { width: 80%; margin: 20px auto; border-collapse: collapse; box-shadow: 0 0 10px rgba(0,0,0,0.1); background-color: white; }
        th, td { border: 1px solid #dee2e6; padding: 12px; text-align: left; }
        th { background-color: #e9ecef; color: #495057; }
        .btn { padding: 5px 10px; text-decoration: none; border-radius: 3px; margin-right: 5px; font-size: 14px; }
        .btn-add { background-color: #28a745; color: white; padding: 10px 15px; display: inline-block; margin-bottom: 15px; } 
        /* Style cho thông báo */
        .alert-success { color: green; font-weight: bold; margin-bottom: 15px; }
        .alert-error { color: red; font-weight: bold; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="header">
        <h2>Quản Lý Danh Mục Sản Phẩm</h2>
        
        <div class="header-actions">
            <a href="<%= request.getContextPath() %>/account" class="btn-account">👤 Quản lý Tài khoản</a>
            <a href="<%= request.getContextPath() %>/logout" class="btn-logout">➡️ Đăng xuất</a>
        </div>
    </div>
    
    <% 
        // Hiển thị thông báo (từ Redirect sau khi Thêm/Sửa/Xóa)
        String alertMsg = request.getParameter("alert");
        if (alertMsg != null) {
            String className = alertMsg.contains("thành công") ? "alert-success" : "alert-error";
            // Giải mã URL vì thông báo được gửi qua redirect
    %>
            <p class="<%= className %>" style="text-align: center;"><%= java.net.URLDecoder.decode(alertMsg, "UTF-8") %></p>
    <%
        }
    %>
    
    <a href="<%= request.getContextPath() %>/admin/category/add" class="btn btn-add">➕ Thêm Danh Mục Mới</a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên Danh Mục</th>
                <th>Icon</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Lấy danh sách Categories từ request
                List<Category> categories = (List<Category>) request.getAttribute("categories");
                if (categories != null) {
                    for (Category cat : categories) {
            %>
                        <tr>
                            <td><%= cat.getCateId() %></td>
                            <td><%= cat.getCateName() %></td>
                            <td>
                                <% if (cat.getIcons() != null && !cat.getIcons().isEmpty()) { %>
                                    <img src="<%= request.getContextPath() %>/image?fname=<%= cat.getIcons() %>" 
                                         alt="<%= cat.getCateName() %> Icon" 
                                         style="width: 50px; height: 50px; object-fit: cover;">
                                <% } else { %>
                                    (Không có ảnh)
                                <% } %>
                            </td>
                            <td>
                                <a href="<%= request.getContextPath() %>/admin/category/edit?id=<%= cat.getCateId() %>" 
                                   class="btn" style="background-color: #ffc107; color: #333;">Sửa</a>
                                
                                <a href="<%= request.getContextPath() %>/admin/category/delete?id=<%= cat.getCateId() %>" 
                                   class="btn" style="background-color: #dc3545; color: white;"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục <%= cat.getCateName() %> không?');">Xóa</a>
                            </td>
                        </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>