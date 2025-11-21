<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Category mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto" style="max-width: 500px;">
        <div class="card-header bg-success text-white">
            <h4 class="mb-0">THÊM CATEGORY MỚI</h4>
        </div>
        <div class="card-body">
            <form method="post">
                <div class="mb-3">
                    <label class="form-label">Tên Category</label>
                    <input type="text" name="cate_name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Icon (tên class FontAwesome hoặc link)</label>
                    <input type="text" name="icons" class="form-control" placeholder="ví dụ: fas fa-home">
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button type="submit" class="btn btn-success me-md-2">Thêm ngay</button>
                    <a href="${pageContext.request.contextPath}/${rolePath}/home" class="btn btn-secondary">Trở về</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>