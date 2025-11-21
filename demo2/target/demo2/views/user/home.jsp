<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User - Danh sách Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Chào User: ${sessionScope.auth.username}</h2>
        <div>
            <a href="update" class="btn btn-warning btn-sm">Thay đổi thông tin</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
        </div>
    </div>
    <h3 class="text-success">DANH SÁCH TẤT CẢ CATEGORY</h3>
    <table class="table table-hover table-bordered">
        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Tên Category</th>
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
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>