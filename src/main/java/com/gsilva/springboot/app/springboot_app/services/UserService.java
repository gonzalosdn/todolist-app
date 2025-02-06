package com.gsilva.springboot.app.springboot_app.services;
import java.util.List;
import com.gsilva.springboot.app.springboot_app.entities.AppUser;


public interface UserService {

    List<AppUser> findAll();
    
    List<AppUser> findAllUsersWithRoles();
    
    AppUser save(AppUser user);

    boolean existsByUsername(String username);


}
