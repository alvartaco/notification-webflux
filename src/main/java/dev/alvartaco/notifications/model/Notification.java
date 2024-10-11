package dev.alvartaco.notifications.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Notification Class, the big one
 */
public class Notification {
    @NotNull
    private Integer notificationId;
    @NotEmpty
    private Message message;
    @NotNull
    private User user;
    @NotEmpty
    private String channelType;
    @NotEmpty
    private String status;
    @NotNull
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Short retryNumber;

    public Notification(Integer notificationId,
                        Message message,
                        User user,
                        String channelType,
                        String status,
                        LocalDateTime createdOn,
                        LocalDateTime updatedOn,
                        Short retryNumber) {
        this.notificationId = notificationId;
        this.message = message;
        this.user = user;
        this.channelType = channelType;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.retryNumber = retryNumber;
    }

    public @NotEmpty String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty String status) {
        this.status = status;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Short getRetryNumber() {
        return retryNumber;
    }

    public void setRetryNumber(Short retryNumber) {
        this.retryNumber = retryNumber;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
