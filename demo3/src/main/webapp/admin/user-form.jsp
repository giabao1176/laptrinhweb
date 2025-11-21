<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<h2>${empty u ? 'Thêm User' : 'Sửa User'}</h2>
<form action="${empty u ? '/admin/users/add' : '/admin/users/edit'}" method="post">
    <c:if test="${not empty u}">
        <input name="username" value="${u.username}" hidden>
    </c:if>
    <div class="mb-3">
        <label>Username</label>
        <input name="username" class="form-control" value="${u.username}" ${not empty u ? 'disabled' : ''}>
    </div>
    <div class="mb-3">
        <label>Password</label>
        <input name="password" type="password" class="form-control" value="${u.password}">
    </div>
    <div class="mb-3">
        <label>Fullname</label>
        <input name="fullname" class="form-control" value="${u.fullname}">
    </div>
    <div class="mb-3">
        <label>Email</label>
        <input name="email" class="form-control" value="${u.email}">
    </div>
    <div class="mb-3">
        <label>Phone</label>
        <input name="phone" class="form-control" value="${u.phone}">
    </div>
    <div class="mb-3">
        <label>Admin</label>
        <select name="admin" class="form-select">
            <option value="true" ${u.admin ? 'selected' : ''}>Có</option>
            <option value="false" ${!u.admin ? 'selected' : ''}>Không</option>
        </select>
    </div>
    <button class="btn btn-primary">Lưu</button>
</form>