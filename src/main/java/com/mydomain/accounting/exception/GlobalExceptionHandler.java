package com.mydomain.accounting.exception;

import static com.mydomain.accounting.exception.ServiceException.ErrorType.Client;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 拦截全局请求异常
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleServiceException(ServiceException e) {
        ErrorResponse errorResponse =
            new ErrorResponseBuilder()
                .statusCode(e.getStatus().value())
                .errorType(e.getErrorType())
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    // 密码错误
    @ExceptionHandler(IncorrectCredentialsException.class)
    ResponseEntity<?> handleInCorrectCredentialsException(IncorrectCredentialsException e) {
        final int status = 400;

        ErrorResponse errorResponse =
            new ErrorResponseBuilder()
                .statusCode(status)
                .errorType(Client)
                .errorCode("INCORRECT_CREDENTIALS")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
