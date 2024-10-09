package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.model.Message;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface IMessageRepository {

        List<Message> findAllByMessageIdDesc() throws MessageException;

        Optional<Message> findByMessageId(Long messageId) throws MessageException;

        void create(@Valid Message message) throws MessageException;

        int count() throws MessageException;

}
