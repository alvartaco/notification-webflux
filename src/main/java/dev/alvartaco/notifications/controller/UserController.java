package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller intended to test/view in the browser the List of loaded users
 */
@RestController
public class UserController {

    private final UserRepository usersRepository;

    public UserController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    List<User> findAll() {
        return usersRepository.findAll();
    }

}
