package com.example.service;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
    public boolean addCategory(Category category) {
        // Có thể thêm logic kiểm tra trùng tên, v.v. ở đây
        return categoryDAO.create(category);
    }
}