package org.example.tiendalemlah.web.config;

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
                        // GET /login → muestra nuestro template login.html
                        .loginPage("/login")
                        // Spring Security sigue procesando el POST a /login internamente
                        .defaultSuccessUrl("/", true)
                        // Si falla el login redirige a /login?error
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(manejadorLogout)
                        .permitAll()
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