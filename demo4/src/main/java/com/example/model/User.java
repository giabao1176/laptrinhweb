package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users")
@Data @NoArgsConstructor @AllArgsConstructor
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Fullname", columnDefinition = "nvarchar(100)")
    private String fullname;
    
    private String phone;
    private String email;
    private boolean admin;
    private boolean active;
    
    @Column(name = "Images", columnDefinition = "nvarchar(500)")
    private String images;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;
}