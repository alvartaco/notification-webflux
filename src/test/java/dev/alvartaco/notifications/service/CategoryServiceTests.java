package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.model.Category;
import dev.alvartaco.notifications.repository.JdbcClientCategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTests {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private JdbcClientCategoryRepository jdbcClientCategoryRepository;

    @Test
    public void getCategoryDTOByCategoryId() throws CategoryException {

        Short testCategoryId = 1;
        String testCategoryName = "Mock Category DTO Name";
        Category mockCategory = new Category(testCategoryId,
                testCategoryName);

        Mockito.when((jdbcClientCategoryRepository
                        .findByCategoryId(testCategoryId)))
                .thenReturn(Optional.of(mockCategory));

        // Act
        CategoryDTO result =
                categoryService.mapToDTO(
                categoryService.getCategoryByCategoryId(testCategoryId));

        //  Assert Existing categoryId
        assertNotNull(result);
        assertEquals(testCategoryId, result.getCategoryId());
        assertEquals(testCategoryName, result.getCategoryName());
    }

    @Test
    public void getCategoryByCategoryId() throws CategoryException {

        Short testCategoryId = 1;
        String testCategoryName = "Mock Category Name";
        Category mockCategory = new Category(testCategoryId,
                testCategoryName);

        Mockito.when((jdbcClientCategoryRepository
                .findByCategoryId(testCategoryId)))
                .thenReturn(Optional.of(mockCategory));

        // Act
        Category result =
                categoryService.getCategoryByCategoryId(testCategoryId);

        // Assert Existing categoryId
        assertNotNull(result);
        assertEquals(testCategoryId, result.getCategoryId());
        assertEquals(testCategoryName, result.getCategoryName());
    }

    @Test
    public void getAllCategoryDTOsByCategoryNameAsc() throws CategoryException {
/*
            List<CategoryDTO> categoriesDTO = new ArrayList<>();
            for (Category category : iCategoryRepository.findAllByCategoryNameAsc()) {
                categoriesDTO.add(mapToDTO(category));
            }
            log.info("#NOTIFICATIONS - OK getAllCategoryDTOsByCategoryNameAsc().");
            return categoriesDTO;
*/
    }

}
