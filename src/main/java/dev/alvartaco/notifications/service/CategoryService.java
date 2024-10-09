package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.model.Category;
import dev.alvartaco.notifications.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Transactional
@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getCategoryDTOByCategoryId(Short categoryId ) throws CategoryException {
        return mapToDTO(categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found")));
    }

    public Category getCategoryByCategoryId(Short categoryId ) throws CategoryException {
        return categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found"));
    }

    public List<CategoryDTO> getAllCategoryDTOsByCategoryNameAsc() throws CategoryException {
        try {
            List<CategoryDTO> categoriesDTO = new ArrayList<>();
            for (Category category : categoryRepository.findAllByCategoryNameAsc()) {
                categoriesDTO.add(mapToDTO(category));
            }
            return categoriesDTO;
        } catch ( Exception e) {
            throw new CategoryException("Category not found");
        }
    }

    /**
     * Used to map important fields to CategoryDTO
     * @return CategoryDTO
     */
    private CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }
}
