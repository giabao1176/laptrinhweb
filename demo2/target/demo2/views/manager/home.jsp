<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager - Quản lý Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-success">Manager: ${sessionScope.auth.username}</h2>
        <div>
            <a href="update" class="btn btn-warning btn-sm">Thay đổi thông tin</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
        </div>
    </div>
    
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/manager/category/add" class="btn btn-success">Thêm Category mới</a>
    </div>
    
    <h3 class="text-success">DANH SÁCH TẤT CẢ CATEGORY</h3>
    
    <table class="table table-hover table-bordered">
        <thead class="table-success">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Icon</th>
                <th>Người tạo (ID)</th> <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr class="${c.user.id == sessionScope.auth.id ? 'table-warning' : ''}">
                    
                    <td>${c.cate_id}</td>
                    <td>${c.cate_name}</td>
                    <td>${c.icons}</td>
                    
                    <td class="text-muted">
                        ${c.user.id} 
                        <c:if test="${c.user.id == sessionScope.auth.id}">
                            <span class="badge bg-success" style="font-size: 0.7rem;">(Của tôi)</span>
                        </c:if>
                    </td>
                    
                    <td>
                        <a href="${pageContext.request.contextPath}/category/view?id=${c.cate_id}" class="btn btn-primary btn-sm">Xem</a>
                        
                        <c:if test="${c.user.id == sessionScope.auth.id}">
                            <a href="${pageContext.request.contextPath}/category/edit?id=${c.cate_id}" class="btn btn-warning btn-sm">Sửa</a>
                            <a href="${pageContext.request.contextPath}/category/delete?id=${c.cate_id}" class="btn btn-danger btn-sm" onclick="return confirm('Xóa thật không?')">Xóa</a>
                        </c:if>
                        
                        <c:if test="${c.user.id != sessionScope.auth.id}">
                            <span class="text-secondary small fst-italic ms-1">Chỉ xem</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>