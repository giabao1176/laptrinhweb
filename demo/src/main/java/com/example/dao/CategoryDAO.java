package com.example.dao;

import com.example.DBContext;
import com.example.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    // ... (Thêm các phương thức CRUD khác vào đây sau này)

    // Phương thức lấy tất cả danh mục
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT cate_id, cate_name, icons FROM Category ORDER BY cate_id ASC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return categoryList;
    }
    public boolean delete(int cateId) {
        String sql = "DELETE FROM Category WHERE cate_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cateId);
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return false;
    }
    public boolean create(Category category) {
        String sql = "INSERT INTO Category (cate_name, icons) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons()); // Tên file icon
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return false;
}
// PHƯƠNG THỨC MỚI: Tìm kiếm theo ID
    public Category findById(int id) {
        String sql = "SELECT cate_id, cate_name, icons FROM Category WHERE cate_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Category category = new Category();
                category.setCateId(rs.getInt("cate_id"));
                category.setCateName(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return null;
    }

    // PHƯƠNG THỨC MỚI: Cập nhật danh mục
    public boolean update(Category category) {
        // Cập nhật cả tên và icons
        String sql = "UPDATE Category SET cate_name = ?, icons = ? WHERE cate_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons());
            ps.setInt(3, category.getCateId());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return false;
    }
}