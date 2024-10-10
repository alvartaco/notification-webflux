package dev.alvartaco.notifications.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * It wil display the notifications list
 */
@Controller
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @GetMapping("/notifications")
    public String message(
            @RequestParam(name="name", required=false, defaultValue="World")
                String name,
                Model model) {
        log.info("#NOTIFICATIONS - INSIDE /notifications");
        return "notifications/index";
    }
}
