package com.gsilva.springboot.app.gestortarea.services;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.gestortarea.entities.AppUser;
import com.gsilva.springboot.app.gestortarea.entities.Role;
import com.gsilva.springboot.app.gestortarea.repositories.RoleRepository;
import com.gsilva.springboot.app.gestortarea.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;     

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> findAll() {

        return (List<AppUser>) userRepository.findAll();
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> findAllUsersWithRoles() {

        return (List<AppUser>) userRepository.findAllUsersWithRoles();
        
    }

    @Override
    @Transactional
    public AppUser save(AppUser user) {

        Optional<Role> optionalRolUser = roleRepository.findByName("ROLE_USER");
        optionalRolUser.ifPresent(role -> user.getRoles().add(role));

        if (user.isAdmin()){
            Optional<Role> optionalRolAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(role -> user.getRoles().add(role));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
        
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }    



}
