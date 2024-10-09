package dev.alvartaco.notifications.notificationengine.factories;

import dev.alvartaco.notifications.notificationengine.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * A factory class for creating instances of
 * INotificationService based on a provided notification type.
 */
@Component
public class NotificationFactory {

    private static final Logger log = LoggerFactory.getLogger(NotificationFactory.class);

    /**
     * A map that contains INotificationService instances
     * mapped to their corresponding notification types.
     */
    private final Map<String, INotificationService> iNotificationServiceMap;

    /**
     * Uses constructor-autowiring to create a new NotificationFactory instance
     * with the provided map of INotificationService instances.
     *
     * @param iNotificationServices a map of INotificationService instances
     */
    public NotificationFactory(Map<String, INotificationService> iNotificationServices) {
        this.iNotificationServiceMap = iNotificationServices;
    }

    /**
     * Returns the INotificationService instance
     * corresponding to the provided notification type.
     *
     * @param notificationType the type of notification
     * @return the INotificationService instance corresponding to the provided notification type
     * @throws RuntimeException if the provided notification type is not supported
     */
    public INotificationService getNotificationService(String notificationType) {
        INotificationService iNotificationService = iNotificationServiceMap.get(notificationType);
        if (iNotificationService == null) {
            log.error("#NOTIFICATIONS - Error getting notification services.");
            throw new RuntimeException("Unsupported notification type");
        }
        return iNotificationService;
    }

    /**
     * Executes the sendNotification() method on the NotificationService
     * instance corresponding to the provided notification type.
     * @param notificationType the type of notification to execute
     * @throws RuntimeException if the provided notification type is not supported
     */
    public void execute(String notificationType) {
        INotificationService notificationService = getNotificationService(notificationType);
        notificationService.sendNotification();
    }
}