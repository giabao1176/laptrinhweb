<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
    <h2>Search Video</h2>
    <form action="${pageContext.request.contextPath}/admin/video/search" method="post" class="d-flex mb-4">
        <input type="text" name="keyword" class="form-control me-2" placeholder="Enter video title...">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    </body>