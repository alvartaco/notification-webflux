package dev.alvartaco.notifications.api;

import dev.alvartaco.notifications.service.CategoryService;
import dev.alvartaco.notifications.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Rest Service to accomplish
 * "It is required to create a system capable of receiving messages"
 */
public class ApiMessageController {
    private static final Logger log = LoggerFactory.getLogger(ApiMessageController.class);
    private final CategoryService categoryService;
    private final MessageService messageService;

    public ApiMessageController(CategoryService categoryService,
                             MessageService messageService) {
        this.categoryService = categoryService;
        this.messageService = messageService;
    }

}
