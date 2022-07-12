package com.lendingwork.supermarket.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ValidationException extends RuntimeException {
    Integer errorCode;
    String errorMessage;

    public ValidationException(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ValidationException(String message, Integer errorCode, String errorMessage) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ValidationException(String message, Throwable cause, Integer errorCode, String errorMessage) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ValidationException(Throwable cause, Integer errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer errorCode, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
