package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    private final CategoryRepository categoryRepository;

    public NotificationController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/notifications")
    public String message(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model) {
        //model.addAttribute("name", categoryRepository.getByCategoryId((short) 1).getCategoryName());
        return "notifications/index";
    }
}
