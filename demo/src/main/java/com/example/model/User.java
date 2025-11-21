package com.example.model;

public class User {
    private String username;
    private String password;
    private String email; // PHẢI CÓ
    private String fullname; // PHẢI CÓ

    public User() {}

    // Constructor cơ bản
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Constructor đầy đủ
    public User(String username, String password, String email, String fullname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; } // BẮT BUỘC
    public String getFullname() { return fullname; } // BẮT BUỘC

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; } // BẮT BUỘC
    public void setFullname(String fullname) { this.fullname = fullname; } // BẮT BUỘC
}