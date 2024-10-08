package dev.alvartaco.notifications.exception;

import java.io.Serial;
import java.io.Serializable;

public class CategoryNotFoundException extends Exception implements Serializable {

        @Serial
        private static final long serialVersionUID = -8086377849040451273L;

        public CategoryNotFoundException(String errorMsg) {
            super(errorMsg);
        }

        public CategoryNotFoundException(String errorMsg, Throwable throwable) {
            super(errorMsg, throwable);
        }

}
