package org.example.tiendalemlah.web.config;

import org.example.tiendalemlah.web.config.ManejadorLogout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeguridadWeb {

    private final ManejadorLogout manejadorLogout;

    public SeguridadWeb(ManejadorLogout manejadorLogout) {
        this.manejadorLogout = manejadorLogout;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/profile/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                )
                .logout(logout -> logout
                        // Nuestro manejador registra el LOGOUT y redirige a /
                        .logoutSuccessHandler(manejadorLogout)
                )
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}