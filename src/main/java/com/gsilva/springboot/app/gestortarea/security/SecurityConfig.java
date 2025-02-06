package com.gsilva.springboot.app.gestortarea.security;
    
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import com.gsilva.springboot.app.gestortarea.security.filter.JwtAuthenticationFilter;
import com.gsilva.springboot.app.gestortarea.security.filter.JwtValidationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz                   
                .requestMatchers("/auth/**","/login", "/error").permitAll()  // El endpoint "/error" está configurado porque, de lo contrario, cuando ocurre un error, como uno de persistencia,
                                                                                         // Spring Boot puede devolver un código de error 403 en lugar del esperado 500. Esto ocurre porque Spring redirige automáticamente
                                                                                         // a "/error" en caso de un error, y si ese endpoint no está configurado, se genera un 403 debido a que está bloqueado.

                //.requestMatchers(HttpMethod.GET,"/auth/users").permitAll()
                //.requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                // .requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.GET,"/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                // .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.DELETE,"/api/products/{id}").hasRole("ADMIN")
                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    // https://chatgpt.com/c/66e22254-7df0-800a-b8b5-bcbca994ba31
    // este metodo filterChain se puede hacer menos hardcodeado armando una tabla de endpoints y relacionarla con la de roles. Fijarse en el link de chatGPT
    // En la clase 213 minuto 11.25 el profesor explica como se podría hacer.

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
        
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return corsBean;

    }

}