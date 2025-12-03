package com.huy.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Table(name = "Category") @Data @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryname;
    private String images;
    private Boolean status = true;
    @ManyToOne @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>();
}