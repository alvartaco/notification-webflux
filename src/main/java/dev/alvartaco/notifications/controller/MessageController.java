package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryNotFoundException;
import dev.alvartaco.notifications.repository.CategoryRepository;
import dev.alvartaco.notifications.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    private final CategoryService categoryService;

    public MessageController( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/message")
    public String message(Model model) {
        List<CategoryDTO> categories = null;
        try {
            categories = categoryService.getAllByCategoryNameAsc();
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("categorySelect", categories);
        return "message/index";
    }

}

