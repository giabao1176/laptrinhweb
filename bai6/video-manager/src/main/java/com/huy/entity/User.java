package com.huy.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Table(name = "Users") @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String images;
    private Boolean admin = false;
    private Boolean active = true;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();
}