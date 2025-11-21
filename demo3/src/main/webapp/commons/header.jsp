<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/home">Demo3</a>
        <div class="navbar-nav ms-auto">
            <%
                com.example.model.User user = (com.example.model.User) session.getAttribute("currentUser");
                if (user != null) {
            %>
                <a class="nav-link" href="<%=request.getContextPath()%>/profile">Xin chào, <%=user.getFullname()%></a>
                <% if (user.isAdmin()) { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/admin/users">Admin</a>
                <% } %>
                <a class="nav-link" href="<%=request.getContextPath()%>/logout">Đăng xuất</a>
            <% } else { %>
                <a class="nav-link" href="<%=request.getContextPath()%>/login">Đăng nhập</a>
            <% } %>
        </div>
    </div>
</nav>