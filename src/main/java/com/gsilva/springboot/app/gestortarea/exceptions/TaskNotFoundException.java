package com.gsilva.springboot.app.gestortarea.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException (Long id) {
        super("Task " + id + " NOT FOUND");
    }

}
