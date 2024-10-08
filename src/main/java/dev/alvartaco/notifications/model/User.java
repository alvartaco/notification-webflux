package dev.alvartaco.notifications.model;

import java.util.List;

/**
 * User Record to be handled in Mock of users
 * with no administration required
 */
public record User(
        Integer userId,
        String userName,
        String userEmail,
        String userPhoneNumber,
        List<Category> userSubscriptions
) {
}
