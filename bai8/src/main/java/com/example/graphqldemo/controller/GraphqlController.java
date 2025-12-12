package com.example.graphqldemo.controller;

import com.example.graphqldemo.model.*;
import com.example.graphqldemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphqlController {

    @Autowired ProductRepository productRepo;
    @Autowired CategoryRepository categoryRepo;
    @Autowired UserRepository userRepo;

    // --- QUERY: Lấy dữ liệu ---

    @QueryMapping
    public List<Product> getAllProductsSorted() {
        // Sắp xếp theo giá tăng dần
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // --- MUTATION: Thay đổi dữ liệu ---

    @MutationMapping
    public Product createProduct(@Argument String title, @Argument Double price, @Argument Long categoryId) {
        Product p = new Product();
        p.setTitle(title);
        p.setPrice(price);
        
        if (categoryId != null) {
            Category c = categoryRepo.findById(categoryId).orElse(null);
            p.setCategory(c);
        }
        
        return productRepo.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        productRepo.deleteById(id);
        return true;
    }
}