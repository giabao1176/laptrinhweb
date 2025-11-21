<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - Quản trị hệ thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-danger fw-bold">ADMIN: ${sessionScope.auth.username}</h2>
        <div>
            <a href="update" class="btn btn-warning btn-sm">Thay đổi thông tin</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark btn-sm">Đăng xuất</a>
        </div>
    </div>
    <div class="row mb-4">
        <div class="col">
            <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-success me-2">Thêm Category</a>
            <a href="${pageContext.request.contextPath}/change-role" class="btn btn-info">Quản lý Role người dùng</a>
        </div>
    </div>
    <h3 class="text-danger">DANH SÁCH TẤT CẢ CATEGORY</h3>
    <table class="table table-hover table-bordered">
        <thead class="table-danger">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Icon</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td>${c.cate_id}</td>
                    <td>${c.cate_name}</td>
                    <td>${c.icons}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category/view?id=${c.cate_id}" class="btn btn-primary btn-sm">Xem</a>
                        <a href="${pageContext.request.contextPath}/category/edit?id=${c.cate_id}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/category/delete?id=${c.cate_id}" class="btn btn-danger btn-sm" onclick="return confirm('Xóa vĩnh viễn?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>