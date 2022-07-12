package com.lendingwork.supermarket.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {
    String errorMessage;
    Integer errorCode;

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

    public ApplicationException(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApplicationException(String message, Integer errorCode, String errorMessage) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
