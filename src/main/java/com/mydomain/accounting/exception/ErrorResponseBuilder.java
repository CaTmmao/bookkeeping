package com.mydomain.accounting.exception;

public class ErrorResponseBuilder {
    private String message;
    private String errorCode;
    private ServiceException.ErrorType errorType;
    private int statusCode;

    public ErrorResponseBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponseBuilder errorCode(String code) {
        this.errorCode = code;
        return this;
    }

    public ErrorResponseBuilder errorType(ServiceException.ErrorType errorType) {
        this.errorType = errorType;
        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(message, errorCode, statusCode, errorType);
    }
}