package com.huy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty; // Import validation
import lombok.*;

@Entity
@Table(name = "Video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @NotEmpty(message = "Tiêu đề video không được để trống")
    private String title;

    private String poster;
    private Integer views = 0;
    
    @NotEmpty(message = "Mô tả không được để trống")
    private String description;
    
    private Boolean active = true;
    
    @ManyToOne 
    @JoinColumn(name = "categoryId")
    private Category category;
}