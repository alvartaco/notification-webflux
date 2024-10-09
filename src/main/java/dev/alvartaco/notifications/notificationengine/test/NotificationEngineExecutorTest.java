package dev.alvartaco.notifications.notificationengine.test;

import dev.alvartaco.notifications.model.ChannelType;
import dev.alvartaco.notifications.notificationengine.factories.NotificationEngineFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * Test class executed at startup for the 3 notification channels
 *
 */
@Service
public class NotificationEngineExecutorTest {
    private final NotificationEngineFactory notificationEngineFactory;

    public NotificationEngineExecutorTest(NotificationEngineFactory notificationEngineFactory) {
        this.notificationEngineFactory = notificationEngineFactory;
    }

    @PostConstruct
    public void test() {
        notificationEngineFactory.execute(ChannelType.EMAIL); // prints `Sending email`
        notificationEngineFactory.execute(ChannelType.PUSH_NOTIFICATION); // prints `Sending push notification`
        notificationEngineFactory.execute(ChannelType.SMS); // prints `Sending SMS`
    }
}
