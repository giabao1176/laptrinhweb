package com.example.graphqldemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    
    @GetMapping("/")
    public String home() {
        return "home"; // Trỏ đến file home.html trong templates
    }
}