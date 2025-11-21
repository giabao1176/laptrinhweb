<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Category - ID: ${category.cate_id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <style>
        .card-header { background: linear-gradient(45deg, #ff9a00, #ff6b00); }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto shadow-lg" style="max-width: 600px;">
        <div class="card-header text-white text-center">
            <h3 class="mb-0"><i class="fas fa-edit me-2"></i>SỬA THÔNG TIN CATEGORY</h3>
        </div>
        <div class="card-body p-4">
            <form method="post">
                <input type="hidden" name="cate_id" value="${category.cate_id}">
                
                <div class="mb-4">
                    <label class="form-label fw-bold text-primary">ID Category</label>
                    <input type="text" class="form-control bg-light" value="${category.cate_id}" disabled>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold text-primary">Tên Category</label>
                    <input type="text" name="cate_name" class="form-control form-control-lg" 
                           value="${category.cate_name}" required autofocus>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold text-primary">Icon (FontAwesome hoặc link)</label>
                    <input type="text" name="icons" class="form-control" 
                           value="${category.icons}" placeholder="Ví dụ: fas fa-laptop, fab fa-java, https://...">
                    <small class="text-muted">Xem icon tại: <a href="https://fontawesome.com/icons" target="_blank">FontAwesome</a></small>
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-warning btn-lg px-5 shadow">
                        <i class="fas fa-save me-2"></i>CẬP NHẬT NGAY
                    </button>
                    <a href="${pageContext.request.contextPath}/${sessionScope.rolePath}/home" 
                       class="btn btn-secondary btn-lg px-5 shadow">
                        <i class="fas fa-arrow-left me-2"></i>Trở về danh sách
                    </a>
                </div>
            </form>
        </div>
        <div class="card-footer text-center text-muted small">
            Người tạo: <strong>${category.user.username}</strong> | 
            Role: <span class="badge bg-info">${sessionScope.auth.roleid == 3 ? 'Admin' : sessionScope.auth.roleid == 2 ? 'Manager' : 'User'}</span>
        </div>
    </div>
</div>
</body>
</html>