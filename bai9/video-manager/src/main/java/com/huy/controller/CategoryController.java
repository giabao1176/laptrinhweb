package com.huy.controller;

import com.huy.entity.Category;
import com.huy.service.CategoryService;
import com.huy.service.UserService;
import jakarta.validation.Valid; // Import validation
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import BindingResult
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService catService;
    private final UserService userService;

    // ĐÃ XÓA hàm checkLogin() vì AuthInterceptor đã lo việc này rồi!

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword, 
                       Model model) {
        // Không cần kiểm tra login ở đây nữa
        model.addAttribute("page", catService.search(keyword, page, size));
        model.addAttribute("keyword", keyword);
        return "category/list";
    }

    @GetMapping({"/add", "/edit/{id}"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        Category c = (id == null) ? new Category() : catService.findById(id);
        
        model.addAttribute("category", c);
        // Load danh sách user để hiển thị trong select box (nếu có)
        model.addAttribute("users", userService.search(null, 0, 100).getContent());
        
        return "category/form";
    }

    // === PHẦN QUAN TRỌNG: VALIDATION ===
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, 
                       BindingResult result, // Biến này phải nằm ngay sau đối tượng được @Valid
                       Model model) {
        
        // 1. Nếu có lỗi validation (ví dụ: tên rỗng)
        if (result.hasErrors()) {
            // Phải load lại danh sách users để dropdown không bị rỗng khi quay lại form
            model.addAttribute("users", userService.search(null, 0, 100).getContent());
            
            // Trả về trang form để hiển thị lỗi
            return "category/form"; 
        }

        // 2. Nếu không có lỗi thì lưu
        catService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        catService.delete(id);
        return "redirect:/admin/categories";
    }
}