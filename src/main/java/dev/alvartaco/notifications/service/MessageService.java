package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.model.Message;
import dev.alvartaco.notifications.model.User;
import dev.alvartaco.notifications.repository.IMessageRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MessageService implements IMessageService{

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final IMessageRepository iMessageRepository;
    private final UserService userService;
    public MessageService(@Qualifier("jdbcClientMessageRepository") IMessageRepository iMessageRepository,
                          UserService userService) {
        this.iMessageRepository = iMessageRepository;
        this.userService = userService;
    }

    /**
     * Main entry point to save messages
     */
    public Integer create(@Valid Message message) throws MessageException {
        return iMessageRepository.create(message);
    }

    @Override
    public void notifyUsers(Message message) throws MessageException {

        List<User> users = userService.getUsersByCategoryId(message.category().getCategoryId());
        if (users != null) {
            log.info("#NOTIFICATIONS - Users {}",  users);
        }
    }
}