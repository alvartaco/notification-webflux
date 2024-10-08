package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryNotFoundException;
import dev.alvartaco.notifications.model.Category;
import dev.alvartaco.notifications.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getCategoryByCategoryId(Short categoryId ) throws CategoryNotFoundException {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return mapToDTO(category);

    }

    public List<CategoryDTO> getAllByCategoryNameAsc() throws CategoryNotFoundException {
        try {
            List<CategoryDTO> categoriesDTO = new ArrayList<>();
            for (Category category : categoryRepository.findAllByCategoryNameAsc()) {
                categoriesDTO.add(mapToDTO(category));
            }
            return categoriesDTO;
        } catch ( Exception e) {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    /**
     * Used to map important fields to CategoryDTO
     * @param Category
     * @return CategoryDTO
     */
    private CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }


}
