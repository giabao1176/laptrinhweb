package com.huy.controller;

import com.huy.entity.Video;
import com.huy.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    // Giả sử bạn có service lấy video hiển thị trang chủ
    // private final VideoService videoService; 

    @GetMapping("/index")
    public String index(Model model) {
        // model.addAttribute("videos", videoService.findAll());
        return "user/index"; // Trả về file giao diện trang chủ (src/main/resources/templates/user/index.html)
    }
}