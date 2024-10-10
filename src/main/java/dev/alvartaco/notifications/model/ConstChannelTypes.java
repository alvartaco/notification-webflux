package dev.alvartaco.notifications.model;

/**
 * And there will be three types of notifications, each requiring its own class to manage
 * the sending logic independently:
 * • SMS
 * • E-Mail
 * • Push Notification
 * It is necessary to design the architecture for sending notifications through various
 * channels. At a minimum, there should be one class for each channel, along with a
 * strategy to select the appropriate channel.
 * Does not have DTO because is static final, it cannot be changed
 */
public class ConstChannelTypes {
    public static final String EMAIL = "EMAIL";
    public static final String SMS = "SMS";
    public static final String PUSH_NOTIFICATION = "PUSH_NOTIFICATION";
}
