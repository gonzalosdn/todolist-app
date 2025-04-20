package com.gsilva.springboot.app.gestortarea.dto;

import java.time.LocalDateTime;

public class TaskDTO {


    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Boolean status;

    public TaskDTO (){
    }

    public TaskDTO(Long id, String title, String description, LocalDateTime createdAt, Boolean status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    


}
