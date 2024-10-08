package dev.alvartaco.notifications.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Message (
        @NotEmpty
        @Positive
        Long messageId,
        Category category,
        @NotEmpty
        String messageBody,
        @NotEmpty
        LocalDateTime createdOn
) {}