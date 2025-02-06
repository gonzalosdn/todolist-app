package com.gsilva.springboot.app.gestortarea.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gsilva.springboot.app.gestortarea.entities.Role;



public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
    
}
