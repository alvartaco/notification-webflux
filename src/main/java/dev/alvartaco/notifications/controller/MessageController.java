package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.CategoryDTO;
import dev.alvartaco.notifications.exception.CategoryNotFoundException;
import dev.alvartaco.notifications.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    public MessageController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Entry point for the message creation Form
     * @param error
     * @param message
     * @param model
     * @return
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
        } catch (CategoryNotFoundException e) {
            // TESTED //
            log.error("#NOTIFICATIONS - Error getting categories /message, fwd to index.");
            return "index";
        }
        model.addAttribute("categorySelect", categories);
        return "message/index";
    }

    /**
     * Method that calls the service to store the message in the DB
     * @param categoryId
     * @param messageBody
     * @param model
     * @return
     */
    @PostMapping("/message/create")
    String createMessage(@RequestParam(name = "categoryId") String categoryId,
                         @RequestParam(name = "messageBody") String messageBody,
                         Model model) {

        List<CategoryDTO> categories;
        try {
            categories = categoryService.getAllCategoryDTOsByCategoryNameAsc();
        } catch (CategoryNotFoundException e) {
            // TESTED //
            log.error("#NOTIFICATIONS - Error getting categories /message/create, fwd to index.");
            return "index";
        }

        /*
         * Validation for existing in Database categoryId
         */
        if (categories.stream().noneMatch(dto -> dto.getCategoryId() == Short.parseShort(categoryId))) {
            // TESTED /
            log.error("#NOTIFICATIONS - Error with received categoryID /message/create");
            return message("ERROR with received Message Category!!!", "", model);
        }
        return message("", "Message Saved..!", model);
    }
}

