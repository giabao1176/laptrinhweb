<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ User/Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Xin chào, ${sessionScope.auth.username} (Role: 
        ${sessionScope.auth.roleid == 1 ? 'User' : 'Admin'})
    </h2>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
    <hr>
    <h3>Danh sách Category</h3>
    <table class="table table-bordered">
        <thead><tr><th>ID</th><th>Tên</th><th>Icon</th><th>Người tạo</th></tr></thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td>${c.cate_id}</td>
                    <td>${c.cate_name}</td>
                    <td><i class="${c.icons}"></i> ${c.icons}</td>
                    <td>${c.user.username}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>