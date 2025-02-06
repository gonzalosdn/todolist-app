package com.gsilva.springboot.app.springboot_app.services;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// esta clase es para implementar la interfaz UserDetails para que en el JpaUserDetailsService pueda retornar mi CustomUserDetails en el metodo loadUserByUsername
// ya que como ese metodo devuelve el tipo de dato UserDetails, y mi clase CustomUserDetails la implementa, asique es de tipo UserDetails y 
//puedo usarla para retornar el id que incluyo en los atributos de mi clase custom

public class CustomUserDetails implements UserDetails { 
    private Long id; // Agregar el ID del usuario
    private String username;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public CustomUserDetails() {        
    }

    public Long getId() {
        return id; // Método para obtener el ID del usuario
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
