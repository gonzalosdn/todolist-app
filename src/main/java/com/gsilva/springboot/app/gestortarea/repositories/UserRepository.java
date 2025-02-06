package com.gsilva.springboot.app.gestortarea.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gsilva.springboot.app.gestortarea.entities.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles")
    List<AppUser> findAllUsersWithRoles();

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);

}
