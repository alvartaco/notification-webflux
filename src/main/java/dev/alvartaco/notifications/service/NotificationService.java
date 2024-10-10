package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.dto.NotificationDTO;
import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.model.Message;
import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.notificationengine.factories.NotificationEngineFactory;
import dev.alvartaco.notifications.repository.INotificationRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final INotificationRepository iNotificationRepository;
    private final UserService userService;
    private final NotificationEngineFactory notificationEngineFactory;
    public NotificationService(@Qualifier("jdbcClientNotificationRepository")
                               INotificationRepository iNotificationRepository,
                               NotificationEngineFactory notificationEngineFactory,
                               UserService userService) {
        this.userService = userService;
        this.iNotificationRepository = iNotificationRepository;
        this.notificationEngineFactory = notificationEngineFactory;
    }

    /**
     * Method that is fired to notify de Subscribers
     * after a subject changes, a new message was created
     * @param message
     * @throws NotificationException
     */
    public void notify(Message message) throws NotificationException {

        NotificationDTO notificationDTO ;
        Integer notificationId;

        List<User> users = userService.getUsersByCategoryId(message.category().getCategoryId());
        log.info("#NOTIFICATIONS - Users {}",  users);

        if (users != null) {

            List<String> userChannelTypes;

            // Loop the users having the message category of the message
            // Finance, sport, movies
            for(User user : users) {

                // Retrieves User's chanel of notification
                // SMS, Email, Push notifications
                userChannelTypes = userService.getUserChannelTypes(user);

                if (userChannelTypes != null) {

                    // Creates the corresponding notifications in the DB
                    for(String channelType : userChannelTypes) {

                        log.info("#NOTIFICATIONS - CREATE NOTIFICATION");
                        notificationDTO = new NotificationDTO(
                                0,
                                message,
                                message.category().getCategoryId(),
                                user,
                                channelType,
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                Short.parseShort("0"));

                        log.info("#NOTIFICATIONS - notificationDTO {}",  notificationDTO);

                        notificationId = create(notificationDTO);

                        // If the notification was created in the DB
                        // The corresponding notification message is sent to the user
                        if (notificationId > 0) {
                             log.info("#NOTIFICATIONS - Start sending corresponding dummy notification to the message " );
                                 notificationEngineFactory.execute(channelType);
                             log.info("#NOTIFICATIONS - End Sending corresponding dummy notification to the message " );
                        }
                    }
                }
            }
        }
    }

    /**
     * I saves a Notification
     */
    public Integer create(@Valid NotificationDTO notificationDTO) throws NotificationException {
           return iNotificationRepository.create(notificationDTO);
    }
}