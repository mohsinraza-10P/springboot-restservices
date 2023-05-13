package com.mohsin.restservices.exceptions;

import java.util.Date;

public class ErrorResponse {

    private Date timestamp;
    private int code;
    private String message;

    public ErrorResponse(Date timestamp, int code, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
