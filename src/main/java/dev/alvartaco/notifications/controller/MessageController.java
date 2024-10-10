package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryException;
import dev.alvartaco.notifications.exception.MessageException;
import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.service.CategoryService;
import dev.alvartaco.notifications.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for message creation handling Page
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

        log.info("#NOTIFICATIONS - START /message");

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
        log.info("#NOTIFICATIONS - END /message");
        return "message/index";
    }

    /**
     * Method that calls the service to store the message in the DB
     */
    @PostMapping("/message/create")
    String createMessage(@RequestParam(name = "categoryId") String categoryId,
                         @RequestParam(name = "messageBody") String messageBody,
                         Model model) {

        log.info("#NOTIFICATIONS - START /message/create");

        /*
         * Validation for existing in Database categoryId
         * Validating messageBody
         */
        try {
            if (categoryService.getAllCategoryDTOsByCategoryNameAsc().stream().noneMatch(dto -> dto.getCategoryId() == Short.parseShort(categoryId))) {
                log.error("#NOTIFICATIONS - Error with received categoryID /message/create");
                return message("ERROR with received Message Category!!!", "", model);
            }
            if (messageBody.isEmpty()) {
                log.error("#NOTIFICATIONS - Error with received messageBody /message/create");
                return message("ERROR with received Message Body!!!", "", model);
            }
        } catch (CategoryException e) {
            log.error("#NOTIFICATIONS - Error getting categories /message/create, fwd to index.");
            return "index";
        }

        log.info("#NOTIFICATIONS - Sending the Notification of message creation");
        try {
            messageService.notify(categoryId, messageBody);
        } catch (Exception e) {
            log.error("#NOTIFICATIONS - Error - messageService.notify(categoryId, messageBody); ");
            return message("Message ERROR NOT Saved..!", "", model);
        }

        log.info("#NOTIFICATIONS - END /message/create");
        return message("", "Message Saved..!", model);
    }
}

