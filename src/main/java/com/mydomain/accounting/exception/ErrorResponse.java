package com.mydomain.accounting.exception;

/**
 * HTTP 响应中返回的错误对象（包含客户端错误和服务器错误）.
 */
public class ErrorResponse {
    private String message; // 错误信息
    private String errorCode; // 简洁版错误信息
    private int statusCode; // 状态码
    private ServiceException.ErrorType errorType; // 错误类型

    public ErrorResponse(String message, String errorCode, int statusCode, ServiceException.ErrorType errorType) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
