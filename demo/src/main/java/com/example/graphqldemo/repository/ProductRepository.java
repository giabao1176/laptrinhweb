package com.example.graphqldemo.repository;

import com.example.graphqldemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm sản phẩm theo ID danh mục
    List<Product> findByCategoryId(Long categoryId);
}