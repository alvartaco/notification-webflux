package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.dto.NotificationDTO;
import dev.alvartaco.notifications.exception.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

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
    public Integer create(NotificationDTO notificationDTO) throws NotificationException {
        try {



            /*            List<User> users = userService.getUsersByCategoryId(message.category().getCategoryId());
            log.info("#NOTIFICATIONS - Users {}",  users);
            if (users != null) {
                log.info("#NOTIFICATIONS - TODO CREATE NOTIFICATION" );

                NotificationDTO notificationDTO = new NotificationDTO(
                )

                log.info("#NOTIFICATIONS - Sending corresponding dummy notification to the message " );

            }
            */





            KeyHolder keyHolder = new GeneratedKeyHolder();

            var updated = jdbcClient.sql("INSERT INTO notification(" +
                            "message_id, " +
                            "message_category_id, " +
                            "user_id, " +
                            "notification_channel_type, " +
                            "notification_created_on, " +
                            "notification_updated_on, " +
                            "notification_retry_number)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)")
                    .params(List.of(
                            notificationDTO.getMessage().messageId(),
                            notificationDTO.getMessage().category().getCategoryId(),
                            notificationDTO.getUser().userId(),
                            notificationDTO.getChannelType(),
                            notificationDTO.getCreatedOn(),
                            notificationDTO.getUpdatedOn(),
                            notificationDTO.getRetryNumber()
                    ))
                    .update(keyHolder);
            Assert.state(updated == 1, "Failed to create Notification, table is empty");

            log.info("#NOTIFICATIONS - END save Notification.");

            return (Integer) keyHolder.getKey();

        } catch (Exception e) {
            log.error("#NOTIFICATIONS - create(Notification notification) ");
            throw new NotificationException(e.toString());
        }
    }

    @Override
    public Integer count() throws NotificationException {
        return 0;
    }
}
