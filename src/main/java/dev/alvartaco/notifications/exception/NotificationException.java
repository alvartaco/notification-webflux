package dev.alvartaco.notifications.exception;

import java.io.Serial;
import java.io.Serializable;

public class NotificationException extends Exception implements Serializable {

        @Serial
        private static final long serialVersionUID = 4560348547277462448L;

        public NotificationException(String errorMsg) {
            super(errorMsg);
        }

        public NotificationException(String errorMsg, Throwable throwable) {
            super(errorMsg, throwable);
        }

}
