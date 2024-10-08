package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryNotFoundException;
import dev.alvartaco.notifications.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller intended for testing,
 * to view in the browser the List of loaded categories
 */
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    List<CategoryDTO> getAllByCategoryNameAsc() throws CategoryNotFoundException {
        return categoryService.getAllCategoryDTOsByCategoryNameAsc();
    }

    @GetMapping("/categories/{categoryId}")
    CategoryDTO getCategoryByCategoryId(@PathVariable short categoryId) throws CategoryNotFoundException {
        return categoryService.getCategoryDTOByCategoryId((short) categoryId);
    }
}
