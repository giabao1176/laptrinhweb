package com.example.demo2.service;

import com.example.demo2.dao.CategoryDAO;
import com.example.demo2.model.Category;
import com.example.demo2.model.User;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    // 1. SỬA PHƯƠNG THỨC NÀY
    public List<Category> getCategories(User user) {
        // Logic cũ: if (role == 2) findByUserId... -> XÓA HOẶC SỬA
        
        // Logic mới: Admin (3) và Manager (2) đều thấy tất cả.
        // Nếu bạn muốn User thường (1) chỉ thấy của mình thì thêm if cho role 1.
        // Ở đây tôi để Manager và Admin lấy tất cả (findAll).
        
        if (user.getRoleid() == 2 || user.getRoleid() == 3) {
             return categoryDAO.findAll();
        }
        
        // Nếu logic cũ user thường (1) cũng thấy tất cả thì chỉ cần: return categoryDAO.findAll();
        // Tạm thời giữ logic user thường giống code cũ của bạn (dòng return cuối).
        return categoryDAO.findAll(); 
    }

    public void addCategory(User user, Category category) {
        category.setUser(user);
        categoryDAO.insert(category);
    }

    // Giữ nguyên logic này: Chỉ Admin(3) hoặc Chủ sở hữu mới được sửa
    public void updateCategory(User user, Category category) {
        Category existing = categoryDAO.findById(category.getCate_id());
        // Manager (2) sẽ rơi vào điều kiện existing.getUser().getId() == user.getId()
        if (existing != null && (user.getRoleid() == 3 || existing.getUser().getId() == user.getId())) {
            existing.setCate_name(category.getCate_name());
            existing.setIcons(category.getIcons());
            categoryDAO.update(existing);
        }
    }

    // Giữ nguyên logic này: Chỉ Admin(3) hoặc Chủ sở hữu mới được xóa
    public void deleteCategory(User user, int id) {
        Category existing = categoryDAO.findById(id);
        if (existing != null && (user.getRoleid() == 3 || existing.getUser().getId() == user.getId())) {
            categoryDAO.delete(id);
        }
    }

    // 2. SỬA PHƯƠNG THỨC NÀY
    // Để Manager bấm "Xem" chi tiết bài của người khác vẫn được
    public Category getCategory(User user, int id) {
        Category category = categoryDAO.findById(id);
        
        // Thêm || user.getRoleid() == 2 vào điều kiện
        // Nghĩa là: Admin (3) OR Manager (2) OR Chủ sở hữu đều xem được chi tiết
        if (category != null && (user.getRoleid() == 3 || user.getRoleid() == 2|| user.getRoleid() == 1 || category.getUser().getId() == user.getId())) {
            return category;
        }
        return null;
    }
}