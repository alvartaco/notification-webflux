package dev.alvartaco.notifications.model;

import java.util.List;

/**
 * User Record to be handled in Mock of users
 * with no administration required
 * That is because does not have userDTO
 *
 * @param userId
 * @param userName
 * @param userEmail
 * @param userPhoneNumber
 * @param userSubscriptions
 * @param userChannels
 *
 */
public record User(
        Integer userId,
        String userName,
        String userEmail,
        String userPhoneNumber,
        List<Category> userSubscriptions,
        List<String> userChannels
) {
}
