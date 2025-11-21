<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2>Profile</h2>
        <c:if test="${param.success == 'true'}">
            <div class="alert alert-success">Cập nhật thành công!</div>
        </c:if>
        <form action="profile" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label>Username</label>
                <input class="form-control" value="${user.username}" disabled>
            </div>
            <div class="mb-3">
                <label>Fullname</label>
                <input name="fullname" class="form-control" value="${user.fullname}">
            </div>
            <div class="mb-3">
                <label>Phone</label>
                <input name="phone" class="form-control" value="${user.phone}">
            </div>
            <div class="mb-3">
                <label>Avatar</label><br>
                <c:if test="${not empty user.images}">
                    <img src="<c:url value='/${user.images}'/>" width="150" class="rounded-circle">
                </c:if>
                <input type="file" name="images" class="form-control mt-2">
            </div>
            <button class="btn btn-primary">Cập nhật</button>
        </form>
    </div>
</div>