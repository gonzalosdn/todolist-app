package com.gsilva.springboot.app.gestortarea.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gsilva.springboot.app.gestortarea.entities.Task;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends CrudRepository<Task,Long> {

    List<Task> findByUserId(Long userId);

    Optional<Task> findByTitle(String title);
    
    @Query("SELECT COUNT(t) > 0 FROM Task t WHERE REPLACE(t.title, ' ', '') = REPLACE(:title, ' ', '')") //esto es para que cuando me pasen el campo "title" y lo valide si existe, si me pasan con muchos espacios me tira un error 500. Para que eso no suceda hago esta consulta personalizada eliminando esos espacios de mas y mer deja solo uno, y asi poder validar correctamente.
    boolean existsByTitle(String title);

    Optional<Task> findByIdAndUserId (Long id, Long userId);

}
