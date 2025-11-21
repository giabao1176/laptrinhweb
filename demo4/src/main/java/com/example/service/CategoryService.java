package com.example.service;

import com.example.dao.CategoryDao;
import com.example.model.Category;
import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    public void insert(Category category) {
        categoryDao.insert(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void delete(int id) {
        try {
            categoryDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Category> search(String keyword) {
        return categoryDao.findByName(keyword);
    }
}