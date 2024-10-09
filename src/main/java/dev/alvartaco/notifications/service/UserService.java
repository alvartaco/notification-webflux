package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository usersRepository;
    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

}
