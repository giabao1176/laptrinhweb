<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - <sitemesh:write property='title'/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex">
        <div class="bg-dark text-white p-3" style="width: 250px; min-height: 100vh;">
            <h3>Admin Panel</h3>
            <jsp:include page="/commons/admin-menu.jsp"/>
        </div>
        
        <div class="flex-grow-1 p-4">
            <sitemesh:write property='body'/>
        </div>
    </div>
</body>
</html>