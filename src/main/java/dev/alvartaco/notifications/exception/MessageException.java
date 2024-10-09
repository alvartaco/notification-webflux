package dev.alvartaco.notifications.exception;

import java.io.Serial;
import java.io.Serializable;

public class MessageException extends Exception implements Serializable {

        @Serial
        private static final long serialVersionUID = -8086377849040451273L;

        public MessageException(String errorMsg) {
            super(errorMsg);
        }

        public MessageException(String errorMsg, Throwable throwable) {
            super(errorMsg, throwable);
        }

}
