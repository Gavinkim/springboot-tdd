package com.honghap.sample.todo;

import java.time.LocalDateTime;

/**
 * Created by gavinkim at 2019-03-31
 */
public class ExceptionResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String details;

    public ExceptionResponse(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
