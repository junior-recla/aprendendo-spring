package com.example.demo.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String exception;

    public ExceptionResponse(Date timestamp, int status, String error, String message, String path, String exception) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getException() {
        return exception;
    }
}
