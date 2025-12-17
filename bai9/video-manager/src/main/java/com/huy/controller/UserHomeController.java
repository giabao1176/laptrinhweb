package com.huy.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huy.entity.User;

@Controller
@RequestMapping("/user") // Định nghĩa tiền tố đường dẫn là /user
public class UserHomeController {

    @GetMapping("/home") // Đường dẫn đầy đủ: /user/home
    public String userHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        // Truyền user xuống view để hiển thị thông tin (nếu cần)
        model.addAttribute("currentUser", user);
        
        return "user/home"; // Trả về file giao diện: templates/user/home.html
    }
}