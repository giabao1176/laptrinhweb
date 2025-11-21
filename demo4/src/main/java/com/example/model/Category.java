package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
@Data @NoArgsConstructor @AllArgsConstructor
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "Categoryname", columnDefinition = "nvarchar(200)")
    private String categoryname;

    private String categorycode;
    private String images;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User user;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Video> videos;
}