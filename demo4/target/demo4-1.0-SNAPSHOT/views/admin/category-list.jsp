<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h2>Category Management</h2>
    <a href="${pageContext.request.contextPath}/views/admin/category-form.jsp" class="btn btn-success mb-3">Add Category</a>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Code</th>
                <th>Image</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td>${c.categoryId}</td>
                    <td>${c.categoryname}</td>
                    <td>${c.categorycode}</td>
                    <td>
                         <c:if test="${not empty c.images}">
                            <img src="${pageContext.request.contextPath}/uploads/${c.images}" width="50" height="50" style="object-fit: cover;">
                         </c:if>
                    </td>
                    <td>${c.status ? 'Active' : 'Inactive'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.categoryId}" class="btn btn-primary btn-sm">Edit</a>
                        <a href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this category?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>