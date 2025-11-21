<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết Category - ${category.cate_name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card mx-auto shadow-lg" style="max-width: 700px;">
        <div class="card-header bg-primary text-white text-center">
            <h2><i class="fas fa-eye me-3"></i>CHI TIẾT CATEGORY</h2>
        </div>
        <div class="card-body">
            <div class="row text-center my-4">
                <div class="col">
                    <h1 style="font-size: 4rem; color: #007bff;">
                        <i class="${category.icons}"></i>
                    </h1>
                    <p class="text-muted">Icon hiển thị</p>
                </div>
            </div>

            <table class="table table-bordered table-striped">
                <tr>
                    <th width="30%" class="table-info">ID Category</th>
                    <td><strong>${category.cate_id}</strong></td>
                </tr>
                <tr>
                    <th class="table-info">Tên Category</th>
                    <td><strong>${category.cate_name}</strong></td>
                </tr>
                <tr>
                    <th class="table-info">Icon Code</th>
                    <td><code>${category.icons}</code></td>
                </tr>
                <tr>
                    <th class="table-info">Người tạo</th>
                    <td>
                        <span class="badge bg-success fs-6">${category.user.username}</span>
                        <small class="text-muted">(ID: ${category.user.id})</small>
                    </td>
                </tr>
                <tr>
                    <th class="table-info">Quyền hiện tại của bạn</th>
                    <td>
                        <span class="badge ${sessionScope.auth.roleid == 3 ? 'bg-danger' : sessionScope.auth.roleid == 2 ? 'bg-warning' : 'bg-secondary'} fs-6">
                            ${sessionScope.auth.roleid == 3 ? 'ADMIN' : sessionScope.auth.roleid == 2 ? 'MANAGER' : 'USER'}
                        </span>
                    </td>
                </tr>
            </table>

            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/${sessionScope.rolePath}/home" 
                   class="btn btn-primary btn-lg px-5">
                    <i class="fas fa-home me-2"></i>Quay lại trang chủ
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>