<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<h2>Quản lý User</h2>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Username</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Admin</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="u" items="${userList}">
            <tr>
                <td>${u.username}</td>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.admin ? 'Có' : 'Không'}</td>
                <td>
                    <a href="<c:url value='/admin/users/edit?username=${u.username}'/>" class="btn btn-sm btn-warning">Sửa</a>
                    <a href="<c:url value='/admin/users/delete?username=${u.username}'/>" class="btn btn-sm btn-danger" onclick="confirm('Xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="<c:url value='/admin/users/add'/>" class="btn btn-primary">Thêm User</a>