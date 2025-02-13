package com.gsilva.springboot.app.gestortarea.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.error = status.getReasonPhrase();
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    

}
