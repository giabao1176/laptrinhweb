<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2>Đăng nhập</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="login" method="post">
            <div class="mb-3">
                <label>Username</label>
                <input name="username" class="form-control">
            </div>
            <div class="mb-3">
                <label>Password</label>
                <input name="password" type="password" class="form-control">
            </div>
            <button class="btn btn-primary">Đăng nhập</button>
        </form>
    </div>
</div>