package com.mydomain.accounting.exception;

/**
 * HTTP 响应中返回的错误对象（包含客户端错误和服务器错误）
 */
public class ErrorException {
    private String message;
    private String code;
    private int statusCode;
    private ServiceException.ErrorType errorType;

    public ErrorException(String message, String code, int statusCode, ServiceException.ErrorType errorType) {
        this.message = message;
        this.code = code;
        this.statusCode = statusCode;
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException.ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ServiceException.ErrorType errorType) {
        this.errorType = errorType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
