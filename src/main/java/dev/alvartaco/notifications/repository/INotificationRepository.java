package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.dto.NotificationDTO;
import dev.alvartaco.notifications.exception.NotificationException;
import jakarta.validation.Valid;

/**
 * Interface to handle different types of Notification Repositories
 */
public interface INotificationRepository {

        Integer create(@Valid NotificationDTO notificationDTO) throws NotificationException;
        Integer count() throws NotificationException;

}
