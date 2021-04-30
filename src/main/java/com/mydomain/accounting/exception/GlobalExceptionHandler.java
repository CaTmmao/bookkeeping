package com.mydomain.accounting.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 拦截全局请求异常
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleServiceException(ServiceException e) {
        ErrorException errorException =
                new ErrorExceptionBuilder()
                        .statusCode(e.getStatus().value())
                        .errorType(e.getErrorType())
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .build();

        return ResponseEntity.status(e.getStatus()).body(errorException);
    }
}
