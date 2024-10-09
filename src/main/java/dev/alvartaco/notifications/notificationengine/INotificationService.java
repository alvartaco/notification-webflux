package dev.alvartaco.notifications.notificationengine;

/**
 * It defines the contract
 * for strategies that provide notification services.
 * Notification services can send notifications
 * and provide the type of notifications that they can send.
 *
 */
public interface INotificationService {
    /**
     * It will Send a notification.
     */
    void sendNotification();
}
