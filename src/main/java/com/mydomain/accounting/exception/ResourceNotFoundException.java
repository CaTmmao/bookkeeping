package com.mydomain.accounting.exception;

import org.springframework.http.HttpStatus;

/**
 * 找不到资源的异常
 */

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
        this.setErrorCode("USER_NOT_FOUND");
        this.setErrorType(ErrorType.Client);
    }
}
