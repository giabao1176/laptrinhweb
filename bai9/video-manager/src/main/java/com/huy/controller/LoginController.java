package com.huy.controller;

import com.huy.entity.User;
import com.huy.service.UserService;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping({"/", "/login"})
    public String login(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // Nếu đã đăng nhập, tự động chuyển về trang tương ứng
            return Boolean.TRUE.equals(user.getAdmin()) 
                   ? "redirect:/admin/videos" 
                   : "redirect:/user/home"; // <--- SỬA Ở ĐÂY (Về trang riêng của User)
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, 
                          @RequestParam String password,
                          HttpSession session, 
                          Model model) {
        
        User u = userService.findByUsername(username);
        
        if (u != null && u.getPassword().equals(password) && Boolean.TRUE.equals(u.getActive())) {
            session.setAttribute("user", u);
            
            // Phân quyền điều hướng sau khi login thành công
            if (Boolean.TRUE.equals(u.getAdmin())) {
                return "redirect:/admin/videos"; // Admin vào quản trị
            } else {
                return "redirect:/user/home";    // <--- SỬA Ở ĐÂY: User thường vào trang user
            }
        }
        
        model.addAttribute("error", "Sai username hoặc password!");
        return "login";
    }
    
    // ... phần logout giữ nguyên


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}