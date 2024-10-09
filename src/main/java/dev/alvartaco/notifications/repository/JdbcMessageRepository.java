package dev.alvartaco.notifications.repository;

import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.model.Message;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Repository JDBC Client for Messages
 */
@Repository
public class JdbcMessageRepository implements IMessageRepository{

    private static final Logger log = LoggerFactory.getLogger(JdbcMessageRepository.class);
    private final JdbcClient jdbcClient;
    public JdbcMessageRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * It returns all Messages order id desc -> LIFO
     * @return List of Message
     */
    public List<Message> findAllByMessageIdDesc() throws MessageException {
        try {
            return jdbcClient.sql("select * from message order by message_id desc")
                    .query(Message.class)
                    .list();
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - List<Message> findAll() ");
            throw new MessageException(e.toString());
        }
    }

    /**
     * Executes the SQL to get a Message by its messageId
     * @param messageId The id of the requested Message
     * @return Optional of Message
     */
    public Optional<Message> findByMessageId(Long messageId) throws MessageException {
        try {
            return jdbcClient.sql("SELECT message_id, category_id, message_body, message_created_on " +
                            "FROM message WHERE message_id = :messageId" )
                    .param("messageId", messageId)
                    .query(Message.class)
                    .optional();
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - Optional<Message> findByMessageId() ");
            throw new MessageException(e.toString());
        }
    }

    /**
     * It inserts a new message
     */
    public void create(@Valid Message message) throws MessageException{
        try {
            if (count() > 0) {
                var updated = jdbcClient.sql("INSERT INTO message(message_id, category_id, message_body, message_created_on) " +
                                "values ( (SELECT ((message_id)+1) FROM message ORDER BY message_id DESC LIMIT 1 )," +
                                "?,?,?)")
                        .params(List.of(message.category().getCategoryId(), message.messageBody(), message.createdOn()))
                        .update();
                Assert.state(updated == 1, "Failed to create Message, table is empty");
            } else {
                var firstRow = jdbcClient.sql("INSERT INTO message(message_id, category_id, message_body, message_created_on) " +
                                "values(1,?,?,?)")
                        .params(List.of(message.category().getCategoryId(), message.messageBody(), message.createdOn()))
                        .update();
                Assert.state(firstRow == 1, "Failed to create Message.");
            }
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - create(Message message) ");
            throw new MessageException(e.toString());
        }
        log.info("#NOTIFICATIONS - END save message.");
    }

    /**
     * Number of Message Rows in the table
     */
    public int count() throws MessageException {
        try {
            return jdbcClient.sql("select message_id from message").query().listOfRows().size();
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - count() ");
            throw new MessageException(e.toString());
        }
    }

}
