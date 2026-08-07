package com.edu.cibertec.proyecto_desarrolloweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.edu.cibertec.proyecto_desarrolloweb.service.DetalleUsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DetalleUsuarioService detalleUsuarioService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(DetalleUsuarioService detalleUsuarioService, PasswordEncoder passwordEncoder) {
        this.detalleUsuarioService = detalleUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) {
        try {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth ->
                            auth.requestMatchers("/auth/**", "/public/**", "/api/**").permitAll()
                                    .anyRequest().authenticated()
                    )
                    .formLogin(login ->
                            login.loginPage("/auth/login")
                                    .defaultSuccessUrl("/home")
                                    .usernameParameter("correo")
                                    .passwordParameter("password")
                                    .permitAll()
                    )
                    .logout(logout ->
                            logout.logoutUrl("/logout")
                                    .logoutSuccessUrl("/auth/login?logout")
                                    .invalidateHttpSession(true)
                                    .deleteCookies("JSESSIONID")
                                    .permitAll()
                    )
                    .authenticationProvider(authenticationProvider());

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException("Error en la configuración de Spring Security", e);
        }
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider
                dao = new DaoAuthenticationProvider(detalleUsuarioService);
        dao.setPasswordEncoder(passwordEncoder);

        return dao;
    }
}