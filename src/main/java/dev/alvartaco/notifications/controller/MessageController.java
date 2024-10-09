package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.model.Message;
import dev.alvartaco.notifications.service.CategoryService;
import dev.alvartaco.notifications.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for message creation handling
 */
@Controller
public class  MessageController {

    static final String MESSAGE = "message";
    static final String ERROR = "error";

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final CategoryService categoryService;
    private final MessageService messageService;

    public MessageController(CategoryService categoryService,
                             MessageService messageService) {
        this.categoryService = categoryService;
        this.messageService = messageService;
    }

    /**
     * Entry point for the message creation Form
     */
    @GetMapping("/message")
    public String message(@RequestParam(name = "error", defaultValue = "") String error,
                          @RequestParam(name = "message", defaultValue = "") String message,
                          Model model) {

        if (!message.isEmpty())
            model.addAttribute(MESSAGE, message);
        if (!error.isEmpty())
            model.addAttribute(ERROR, error);

        List<CategoryDTO> categories;
        try {
            categories = categoryService.getAllCategoryDTOsByCategoryNameAsc();
        } catch (CategoryException e) {
            // TESTED //
            log.error("#NOTIFICATIONS - Error getting categories /message, fwd to index.");
            return "index";
        }
        model.addAttribute("categorySelect", categories);
        return "message/index";
    }

    /**
     * Method that calls the service to store the message in the DB
     */
    @PostMapping("/message/create")
    String createMessage(@RequestParam(name = "categoryId") String categoryId,
                         @RequestParam(name = "messageBody") String messageBody,
                         Model model) {

        /*
         * Validation for existing in Database categoryId
         */
        try {
            if (categoryService.getAllCategoryDTOsByCategoryNameAsc().stream().noneMatch(dto -> dto.getCategoryId() == Short.parseShort(categoryId))) {
                // TESTED /
                log.error("#NOTIFICATIONS - Error with received categoryID /message/create");
                return message("ERROR with received Message Category!!!", "", model);
            }
        } catch (CategoryException e) {
            // TESTED //
            log.error("#NOTIFICATIONS - Error getting categories /message/create, fwd to index.");
            return "index";
        }

        try {
            Message message = new Message(
                    null,
                    categoryService.getCategoryByCategoryId(Short.valueOf(categoryId)),
                    messageBody.trim(),
                    LocalDateTime.now());

            messageService.create(message);

        } catch (MessageException | CategoryException e) {
            log.error("#NOTIFICATIONS - Error saving message/message/create, fwd to index.");
            return message("ERROR saving message!!!", "", model);
        }

        return message("", "Message Saved..!", model);
    }
}

