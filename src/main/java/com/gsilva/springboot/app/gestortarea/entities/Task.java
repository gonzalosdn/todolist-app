package com.gsilva.springboot.app.gestortarea.entities;

import java.time.LocalDateTime;

import com.gsilva.springboot.app.gestortarea.validation.IsExistDb;
import com.gsilva.springboot.app.gestortarea.validation.IsRequired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    @IsExistDb
    private String title;

    @IsRequired
 // @NotBlank
    private String description; 

    @NotNull
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @NotNull
    private Boolean status;
        
    private Long userId; // esta relacion seria un ManyToOne con mi clase User, pero como al id lo saco del jwt y no por relacionar las tablas, no lo anoto con nada


    public Task(){
        this.createdAt = LocalDateTime.now();
        this.status = false;
    }

    public Task(String title, String description) {   

        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.status = false;       
    }

    public Task(String title, String description,Long userId) {   

        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.status = false;
        this.userId = userId;
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

    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
