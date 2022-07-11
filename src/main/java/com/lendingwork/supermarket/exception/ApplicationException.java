package com.lendingwork.supermarket.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {
    String errorMessage;

    public ApplicationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ApplicationException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ApplicationException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public ApplicationException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
