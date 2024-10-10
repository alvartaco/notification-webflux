package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository usersRepository;
    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getUsersByCategoryId(short categoryId){

        return usersRepository.findAll().stream()
            .filter(
                user -> user.userSubscriptions()
                        .stream()
                        .anyMatch(category -> category.getCategoryId() == categoryId )
            )
            .toList();

    }
}
