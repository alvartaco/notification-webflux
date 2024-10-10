package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository usersRepository;
    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * it Finds all users that are subscribed to a Message Category
     * @param categoryId
     * @return
     */
    public List<User> getUsersByCategoryId(short categoryId){

        return usersRepository.findAll().stream()
            .filter(
                user -> user.userSubscriptions()
                        .stream()
                        .anyMatch(category -> category.getCategoryId() == categoryId )
            )
            .toList();
    }

    /**
     * it finds the channels that a user is subscribed to receive the notifications
     * @param user
     * @return
     */
    public List<String> getUserChannelTypes(User user){
        return  user.userChannels()
                    .stream()
                    .toList();
    }
}
