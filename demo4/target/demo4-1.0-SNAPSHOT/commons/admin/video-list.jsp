<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head><title>Video List</title></head>
<body>
    <h2>Video Management</h2>
    <a href="${pageContext.request.contextPath}/views/admin/video-form.jsp" class="btn btn-primary mb-3">Add New Video</a>
    <a href="${pageContext.request.contextPath}/admin/video/search" class="btn btn-secondary mb-3">Search Video</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Poster</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${videos}">
                <tr>
                    <td>${v.videoId}</td>
                    <td>${v.title}</td>
                    <td>
                        <c:if test="${not empty v.poster}">
                            <img src="${pageContext.request.contextPath}/uploads/${v.poster}" width="100" />
                        </c:if>
                    </td>
                    <td>
                        <a href="#" class="btn btn-warning btn-sm">Edit</a>
                        <a href="#" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>