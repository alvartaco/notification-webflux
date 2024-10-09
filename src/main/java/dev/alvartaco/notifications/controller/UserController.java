package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller intended to test/view in the browser the List of loaded users
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository usersRepository;
    public UserController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    List<User> findAll() {
        log.info("#NOTIFICATIONS - INSIDE /users");
        return usersRepository.findAll();
    }

}
