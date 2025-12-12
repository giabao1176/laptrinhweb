package com.example.graphqldemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer quantity;
    
    @Column(name = "[desc]") // SQL Server cần ngoặc vuông cho từ khóa desc
    private String desc;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
}