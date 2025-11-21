<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Role người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2 class="text-danger text-center mb-4">QUẢN LÝ ROLE NGƯỜI DÙNG</h2>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role hiện tại</th>
                <th>Thay đổi Role</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.username}</td>
                    <td>
                        <span class="badge ${u.roleid==1?'bg-secondary':u.roleid==2?'bg-warning':'bg-danger'}">
                            ${u.roleid==1?'User':u.roleid==2?'Manager':'Admin'}
                        </span>
                    </td>
                    <td>
                        <form method="post" class="d-inline">
                            <input type="hidden" name="userId" value="${u.id}">
                            <select name="newRole" class="form-select form-select-sm d-inline w-auto">
                                <option value="1" ${u.roleid==1?'selected':''}>User</option>
                                <option value="2" ${u.roleid==2?'selected':''}>Manager</option>
                                <option value="3" ${u.roleid==3?'selected':''}>Admin</option>
                            </select>
                            <button type="submit" class="btn btn-primary btn-sm ms-2">Cập nhật</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Quay lại trang Admin</a>
    </div>
</div>
</body>
</html>