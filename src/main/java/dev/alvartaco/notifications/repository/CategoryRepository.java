package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository (Pattern used for Data Access) of DataBase Category Records
 */
@Repository
public class CategoryRepository {

    private static final Logger log = LoggerFactory.getLogger(CategoryRepository.class);

    private final JdbcClient jdbcClient;

    public CategoryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * It returns al Database Categories order by name asc
     * @return List of Category
     */
    public List<Category> findAllByCategoryNameAsc() {
        try {
            return jdbcClient.sql("select * from category order by category_name asc")
                    .query(Category.class)
                    .list();
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - List<Category> findAllByCategoryNameAsc() ");
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes the SQL to get a Category by its categoryId
     * @param categoryId The id of the requested Category
     * @return Optional of Category
     */
    public Optional<Category> findByCategoryId(Short categoryId) {
        try {
            return jdbcClient.sql("SELECT category_id, category_name FROM category WHERE category_id = :categoryId" )
                    .param("categoryId", categoryId)
                    .query(Category.class)
                    .optional();
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - Optional<Category> findByCategoryId() ");
            throw new RuntimeException(e);
        }
    }

    /*
     * Returns the Category if is present in Optional Object or null
     * @param categoryId The id of the requested Category
     * @return a Category or null
     *
    public Category getByCategoryId(short categoryId) {

        Optional<Category> categories = findByCategoryId(categoryId);

        if(categories.isEmpty()) {
            if (log.isErrorEnabled()) {
                log.error("There is no category with categoryId : {}", categoryId);
            }
            return null;
        }
        return categories.get();
    }
    */
}
