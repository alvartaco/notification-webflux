package dev.alvartaco.notifications.controller;

import com.github.javafaker.Faker;
import dev.alvartaco.notifications.exception.NotificationException;
import dev.alvartaco.notifications.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.IntStream;

/**
 * It wil display the notifications list
 */
@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final Faker faker;
    private final NotificationService notificationService;
    public NotificationController(
                                    NotificationService notificationService,
                                  Faker faker ) {
        this.notificationService = notificationService;
        this.faker = faker;
    }

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public String list(Model model) throws NotificationException {
        model.addAttribute("rows", notificationService.getAllNotificationsDisplayDTOsLiFo());
        return "notifications/index";
    }

}
