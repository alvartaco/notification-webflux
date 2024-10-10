package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.model.Message;
import dev.alvartaco.notifications.repository.IMessageRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MessageService implements IMessageService{

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final IMessageRepository iMessageRepository;
    private final NotificationService notificationService;
    public MessageService(@Qualifier("jdbcClientMessageRepository")
                          IMessageRepository iMessageRepository,
                          NotificationService notificationService) {
        this.iMessageRepository = iMessageRepository;
        this.notificationService = notificationService;
    }

    /**
     * Main entry point to save messages
     */
    public Integer create(@Valid Message message) throws MessageException {
        log.info("#NOTIFICATIONS - Going to iMessageRepository.create(message).");
        return iMessageRepository.create(message);
    }

    @Override
    public void notify(Message message) throws NotificationException {

        try {
            log.info("#NOTIFICATIONS - Going to notify(Message message)");
            notificationService.notify(message);
        } catch (NotificationException e) {
            log.error("#NOTIFICATIONS - Error  notificationService.create(message)");
            throw new NotificationException(e.toString());
        }

    }
}