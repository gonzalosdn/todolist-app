package com.gsilva.springboot.app.gestortarea.dto;

public class CreateTaskDTO {

    private String title;
    private String description;

    public CreateTaskDTO() {
    }

    public CreateTaskDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }   
    

}
