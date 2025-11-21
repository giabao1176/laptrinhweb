<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - HCMUTE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .login-box { max-width: 420px; margin: 100px auto; padding: 40px; border-radius: 15px; box-shadow: 0 15px 35px rgba(0,0,0,0.3); background: white; }
        .btn-login { background: #007bff; border: none; }
        .btn-login:hover { background: #0056b3; }
    </style>
</head>
<body>
<div class="login-box">
    <div class="text-center mb-4">
        <h3 class="text-primary fw-bold">ĐĂNG NHẬP HỆ THỐNG</h3>
        <p class="text-muted">Lập trình Web - WEBPR330479</p>
    </div>
    <% if(request.getAttribute("error") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>
    <form method="post">
        <div class="mb-3">
            <label class="form-label">Tên đăng nhập</label>
            <input type="text" name="username" class="form-control" required autofocus>
        </div>
        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary btn-login w-100 py-2 fw-bold">ĐĂNG NHẬP</button>
    </form>
    <div class="text-center mt-3">
        <small>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register" class="text-decoration-none">Đăng ký ngay</a></small>
    </div>
</div>
</body>
</html>