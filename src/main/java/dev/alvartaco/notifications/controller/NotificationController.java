package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    private final CategoryService categoryService;

    public NotificationController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/notifications")
    public String message(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model) {
        //model.addAttribute("name", categoryRepository.getByCategoryId((short) 1).getCategoryName());
        return "notifications/index";
    }
}
