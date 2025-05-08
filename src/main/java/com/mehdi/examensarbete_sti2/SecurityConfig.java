package com.mehdi.examensarbete_sti2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    // Konfigurerar säkerheten för applikationen
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Inaktivera CSRF-skydd
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/recipes/**").permitAll() // Offentlig åtkomst till dessa vägar
                        .anyRequest().authenticated() // Skydda övriga vägar
                )
                .formLogin(Customizer.withDefaults()) // Standardformulär för inloggning
                .logout(logout -> logout.permitAll()); // Tillåt alla att logga ut

        return http.build();
    }

    // PasswordEncoder-bean för att kryptera lösenord
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Skapar en AuthenticationManager med en användare i minnet
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    // Skapa en användartjänst i minnet
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("adminpass")) // Kryptera lösenordet
                        .roles("USER")
                        .build()
        );
    }
}
