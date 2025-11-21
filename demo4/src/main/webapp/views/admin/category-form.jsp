<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body>
    <h2>${empty cate ? 'Add New Category' : 'Edit Category'}</h2>
    
    <c:set var="actionUrl" value="${empty cate ? '/admin/category/create' : '/admin/category/update'}"/>

    <form action="${pageContext.request.contextPath}${actionUrl}" method="post" enctype="multipart/form-data">
        <c:if test="${not empty cate}">
            <input type="hidden" name="categoryId" value="${cate.categoryId}">
        </c:if>

        <div class="mb-3">
            <label>Category Name</label>
            <input type="text" name="categoryname" class="form-control" value="${cate.categoryname}" required>
        </div>
        <div class="mb-3">
            <label>Category Code</label>
            <input type="text" name="categorycode" class="form-control" value="${cate.categorycode}">
        </div>
        <div class="mb-3">
            <label>Image</label>
            <input type="file" name="images" class="form-control">
            <c:if test="${not empty cate.images}">
                <img src="${pageContext.request.contextPath}/uploads/${cate.images}" width="100" class="mt-2">
            </c:if>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="status" value="true" ${cate.status ? 'checked' : ''}>
            <label class="form-check-label">Active</label>
        </div>
        
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/admin/category" class="btn btn-secondary">Cancel</a>
    </form>
</body>