package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.exception.MessageException;
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
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private final IMessageRepository messageRepository;

    public MessageService(@Qualifier("jdbcMessageRepository") IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void create(@Valid Message message) throws MessageException {
        messageRepository.create(message);
    }

}