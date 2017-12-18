package com.tobz.clean.data.network.error;

import java.io.Serializable;

/**
 * Rest exception handler.
 */

public class RestApiException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2994148092066356020L;

    public static final int UNAUTHORIZED = 401;

    private String status;
    private Integer statusCode;

    public RestApiException(String message, String status, Integer statusCode) {
        super(message);
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
