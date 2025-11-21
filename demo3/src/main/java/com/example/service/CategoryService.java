package com.example.service;

import com.example.dao.CategoryDao;
import com.example.model.Category;
import java.util.List;

public class CategoryService {
    private final CategoryDao dao = new CategoryDao();

    public List<Category> findAll() {
        return dao.findAll();
    }

    public Category findById(int id) {
        return dao.findById(id);
    }

    public List<Category> findByUsername(String username) {
        return dao.findByUsername(username);
    }

    public void save(Category category) {
        dao.save(category);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}