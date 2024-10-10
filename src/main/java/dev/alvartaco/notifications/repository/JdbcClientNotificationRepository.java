package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

/**
 * Repository of DataBase Notification Records
 * JDBC Client
 */
@Repository
public class JdbcClientNotificationRepository implements INotificationRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientNotificationRepository.class);
    private final JdbcClient jdbcClient;
    public JdbcClientNotificationRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    @Override
    public Integer create(Notification notification) throws NotificationException {
        return 0;
    }

    @Override
    public Integer count() throws NotificationException {
        return 0;
    }
}
