package com.mydomain.accounting.exception;

import org.springframework.http.HttpStatus;

/**
 * 源于服务器错误的 HTTP 请求异常
 */
public class ServiceException extends RuntimeException {
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    private HttpStatus status;

    private ErrorType errorType;

    private String errorCode;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
