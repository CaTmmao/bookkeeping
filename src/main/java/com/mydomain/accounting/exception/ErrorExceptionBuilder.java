package com.mydomain.accounting.exception;

public class ErrorExceptionBuilder {
    private String message;
    private String code;
    private ServiceException.ErrorType errorType;
    private int statusCode;

    public ErrorExceptionBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorExceptionBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorExceptionBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ErrorExceptionBuilder errorType(ServiceException.ErrorType errorType) {
        this.errorType = errorType;
        return this;
    }

    public ErrorException build() {
        return new ErrorException(message, code, statusCode, errorType);
    }
}