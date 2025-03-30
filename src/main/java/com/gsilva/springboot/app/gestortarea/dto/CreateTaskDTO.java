package com.gsilva.springboot.app.gestortarea.dto;

import com.gsilva.springboot.app.gestortarea.validation.IsExistDb;
import com.gsilva.springboot.app.gestortarea.validation.IsRequired;

public class CreateTaskDTO {

    @IsRequired
    @IsExistDb
    private String title;

    @IsRequired    
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
