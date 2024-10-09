package dev.alvartaco.notifications.exception;

import java.io.Serial;
import java.io.Serializable;

public class MessageException extends Exception implements Serializable {

        @Serial
        private static final long serialVersionUID = 6197008547277462448L;

        public MessageException(String errorMsg) {
            super(errorMsg);
        }

        public MessageException(String errorMsg, Throwable throwable) {
            super(errorMsg, throwable);
        }

}
