<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cập nhật thông tin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Cập nhật thông tin</h5>
            <form method="post">
                <div class="mb-3">
                    <label class="form-label">Username mới (optional)</label>
                    <input type="text" name="newUsername" class="form-control">
                </div>
                <div class="mb-3">
                    <label class="form-label">Password mới (optional)</label>
                    <input type="password" name="newPassword" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
                <a href="home" class="btn btn-secondary">Trở về</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>