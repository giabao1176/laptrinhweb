package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Videos")
@Data @NoArgsConstructor @AllArgsConstructor
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
    @Id
    @Column(name = "VideoId")
    private String videoId;

    @Column(name = "Title", columnDefinition = "nvarchar(200)")
    private String title;

    private String poster;
    private int views;
    
    @Column(name = "Description", columnDefinition = "nvarchar(MAX)")
    private String description;
    
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}