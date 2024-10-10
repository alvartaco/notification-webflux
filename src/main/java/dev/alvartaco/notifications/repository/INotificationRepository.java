package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.model.Notification;
import jakarta.validation.Valid;

/**
 * Interface to handle different types of Notification Repositories
 */
public interface INotificationRepository {

        Integer create(@Valid Notification notification) throws NotificationException;
        Integer count() throws NotificationException;

}
