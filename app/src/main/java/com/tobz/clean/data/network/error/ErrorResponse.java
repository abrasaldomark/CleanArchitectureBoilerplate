package com.tobz.clean.data.network.error;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Error response pojo.
 */

public class ErrorResponse {

    @SerializedName(value = "status_code")
    @Expose
    private Integer statusCode;

    @Expose
    private String message;

    @Expose
    private String status;

    public ErrorResponse(Integer statusCode, String message, String status) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
