package dev.alvartaco.notifications.notificationengine.strategies;

import dev.alvartaco.notifications.model.ChannelType;
import dev.alvartaco.notifications.notificationengine.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(ChannelType.SMS)
public class SmsNotificationService implements INotificationService {

    private static final Logger log = LoggerFactory.getLogger(SmsNotificationService.class);

    @Override
    public void sendNotification() {
        log.info("#NOTIFICATIONS - NOTIFICATION - Sent by SMS");
    }
}
