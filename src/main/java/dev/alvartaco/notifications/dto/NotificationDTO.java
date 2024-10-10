package dev.alvartaco.notifications.dto;

import dev.alvartaco.notifications.model.Message;
import dev.alvartaco.notifications.model.User;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Integer notificationId;
    private Message message;
    private Short categoryId;
    private User user;
    private String channelType;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Short retryNumber;

    public NotificationDTO(Integer notificationId,
                           Message message,
                           Short categoryId,
                           User user,
                           String channelType,
                           LocalDateTime createdOn,
                           LocalDateTime updatedOn,
                           Short retryNumber) {
        this.notificationId = notificationId;
        this.message = message;
        this.categoryId = categoryId;
        this.user = user;
        this.channelType = channelType;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.retryNumber = retryNumber;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
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
