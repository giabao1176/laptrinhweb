<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
    <h2>Add New Video</h2>
    <form action="${pageContext.request.contextPath}/admin/video/create" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label>Video ID (Code)</label>
            <input type="text" name="videoId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Title</label>
            <input type="text" name="title" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control"></textarea>
        </div>
        <div class="mb-3">
            <label>Poster (Image)</label>
            <input type="file" name="poster" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Create Video</button>
        <a href="${pageContext.request.contextPath}/admin/video" class="btn btn-secondary">Back</a>
    </form>
</body>