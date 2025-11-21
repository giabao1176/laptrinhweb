package com.example.demo2.model;

import jakarta.persistence.*;
// Nếu muốn dùng annotation riêng của Hibernate (tùy chọn)
// import org.hibernate.annotations.Nationalized; 

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int cate_id;

    // --- SỬA ĐOẠN NÀY ---
    // Thêm columnDefinition = "nvarchar(255)" để SQL Server hiểu đây là chuỗi Unicode
    @Column(name = "cate_name", nullable = false, length = 255, columnDefinition = "nvarchar(255)")
    private String cate_name;

    // Cũng nên sửa icon thành nvarchar nếu icon chứa ký tự đặc biệt hoặc tiếng Việt
    @Column(name = "icons", length = 255, columnDefinition = "nvarchar(255)")
    private String icons;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getter setter giữ nguyên
    public int getCate_id() { return cate_id; }
    public void setCate_id(int cate_id) { this.cate_id = cate_id; }
    public String getCate_name() { return cate_name; }
    public void setCate_name(String cate_name) { this.cate_name = cate_name; }
    public String getIcons() { return icons; }
    public void setIcons(String icons) { this.icons = icons; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}