package dev.alvartaco.notifications.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record Message (
        Integer messageId,
        @NotNull
        Category category,
        @NotEmpty
        String messageBody,
        @NotEmpty
        LocalDateTime createdOn
) {}