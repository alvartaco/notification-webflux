package dev.alvartaco.notifications.model;

public record Message (
        Long messageId,
        Category category,
        String messageBody,
        Integer messageVersion
) {}