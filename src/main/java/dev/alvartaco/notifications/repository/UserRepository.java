package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.model.ChannelType;
import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.service.CategoryService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Repository of Users
 * Also used to Initial Load of Mock User Records
 * into memory
 */
@Repository
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final CategoryService categoryService;
    public UserRepository(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    private final List<User> users = new ArrayList<>();
    public List<User> findAll() {
        return users;
    }

    /**
     * Initializes the List of Mock users
     */
    @PostConstruct
    private void init() {
        try {
            log.info("#NOTIFICATIONS - START to save users.");
            users.add(new User(
                    1,
                    "Alejandro Sporty, SMS",
                    "alejandro.sporty@alvartaco.dev",
                    "+5492616803201",
                    List.of(categoryService.getCategoryByCategoryId((short) 3)),
                    List.of(ChannelType.SMS)
            ));
            users.add(new User(
                    2,
                    "Alejandro Fintech",
                    "alejandro.fintech@alvartaco.dev",
                    "+5492616803202",
                    List.of(categoryService.getCategoryByCategoryId((short) 1)),
                    List.of(ChannelType.EMAIL)

            ));
            users.add(new User(
                    3,
                    "Alejandro Cine",
                    "alejandro.cine@alvartaco.dev",
                    "+5492616803203",
                    List.of(categoryService.getCategoryByCategoryId((short) 2)),
                    List.of(ChannelType.PUSH_NOTIFICATION)
            ));
            users.add(new User(
                    4,
                    "Alejandro ALL Categories",
                    "alejandro.all@alvartaco.dev",
                    "+5492616803204",
                    Arrays.asList(
                            categoryService.getCategoryByCategoryId((short) 3),
                            categoryService.getCategoryByCategoryId((short) 1),
                            categoryService.getCategoryByCategoryId((short) 2)
                    ),
                    Arrays.asList(
                            ChannelType.EMAIL,
                            ChannelType.SMS,
                            ChannelType.PUSH_NOTIFICATION
                    )
            ));
            log.info("#NOTIFICATIONS - Users {}",  users);
            log.info("#NOTIFICATIONS - END saving users.");
        } catch (CategoryException e) {
            log.error("#NOTIFICATIONS - Error getting categories when creating users.");
        }
    }
}
