package com.lendingwork.supermarket.exception;

import com.lendingwork.supermarket.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionAdvice extends ExceptionInInitializerError {

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<ErrorResponse> handleForensicApplicationException(ApplicationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
