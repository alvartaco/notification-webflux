package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.model.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Repository (Pattern used for Data Access)
 * Also used to Initial Load of Mock User Records
 * into memory
 */
@Repository
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    private final CategoryRepository categoryRepository;

    public UserRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    /**
     * Initializes the List of users
     */
    @PostConstruct
    private void init() {
        users.add(new User(
                1,
                "Alejandro Sporty",
                "alejandro.sporty@alvartaco.dev",
                "+5492616803201",
                List.of(categoryRepository.getByCategoryId((short) 3))
        ));
        users.add(new User(
                2,
                "Alejandro Fintech",
                "alejandro.fintech@alvartaco.dev",
                "+5492616803202",
                List.of(categoryRepository.getByCategoryId((short) 1))
        ));
        users.add(new User(
                3,
                "Alejandro Cine",
                "alejandro.cine@alvartaco.dev",
                "+5492616803203",
                List.of(categoryRepository.getByCategoryId((short) 2))
        ));
        users.add(new User(
                4,
                "Alejandro ALL Categories",
                "alejandro.all@alvartaco.dev",
                "+5492616803204",
                Arrays.asList(
                    categoryRepository.getByCategoryId((short) 3),
                    categoryRepository.getByCategoryId((short) 1),
                    categoryRepository.getByCategoryId((short) 2)
                )
        ));

        log.info("#NOTIFICATIONS - Users Created.");

    }
}
