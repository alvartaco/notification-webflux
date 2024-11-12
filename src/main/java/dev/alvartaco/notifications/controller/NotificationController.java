package dev.alvartaco.notifications.controller;

import dev.alvartaco.notifications.dto.NotificationDisplayDTO;
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

/**
 * It wil display the notifications list
 */
@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;
    public NotificationController(
                                    NotificationService notificationService ) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public String list(Model model) throws NotificationException {
        log.info("#NOTIFICATIONS - public String list(Model model)");
        List<NotificationDisplayDTO> notificationDisplayDTOS = notificationService.getAllNotificationsDisplayDTOsLiFo();
        model.addAttribute("displayTable", (notificationDisplayDTOS.isEmpty() ? "none" : "block"));
        model.addAttribute("rows", notificationDisplayDTOS);
        return "notifications/index";
    }

}
