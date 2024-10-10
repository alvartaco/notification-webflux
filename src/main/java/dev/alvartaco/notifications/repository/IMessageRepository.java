package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.model.Message;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * Interface to handle different types of Message Repositories
 */
public interface IMessageRepository {

        List<Message> findAllByMessageIdDesc() throws MessageException;
        Optional<Message> findByMessageId(Integer messageId) throws MessageException;
        Integer create(@Valid Message message) throws MessageException;
        Integer count() throws MessageException;
}
