package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Interface to handle different types of Category Repositories
 */
public interface ICategoryRepository {

    List<Category> findAllByCategoryNameAsc() throws CategoryException;
    Optional<Category> findByCategoryId(Short categoryId) throws CategoryException;

}
