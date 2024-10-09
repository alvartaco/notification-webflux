package dev.alvartaco.notifications.notificationengine.test;

import dev.alvartaco.notifications.model.ChannelType;
import dev.alvartaco.notifications.notificationengine.factories.NotificationFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * Test class executed at startup for the 3 notification channels
 *
 */
@Service
public class NotificationExecutorTest {
    private final NotificationFactory notificationFactory;

    public NotificationExecutorTest(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @PostConstruct
    public void test() {
        notificationFactory.execute(ChannelType.EMAIL); // prints `Sending email`
        notificationFactory.execute(ChannelType.PUSH_NOTIFICATION); // prints `Sending push notification`
        notificationFactory.execute(ChannelType.SMS); // prints `Sending SMS`
    }
}
