package com.gsilva.springboot.app.gestortarea.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.gestortarea.entities.AppUser;
import com.gsilva.springboot.app.gestortarea.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe", username));
        }

        AppUser user = optionalUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

        // Retorna un CustomUserDetails en lugar de User, que es una implementacion de UserDetails
        return new CustomUserDetails(user.getId(), // Agregar el ID del usuario
                                      user.getUsername(),
                                      user.getPassword(),
                                      user.isEnabled(), // Agregar el estado de habilitación
                                      authorities);
    }
}
