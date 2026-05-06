package org.example.tiendalemlah.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadWeb {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Consola H2: solo usuarios autenticados
                        .requestMatchers("/h2-console/**").authenticated()
                        // Área de administración: solo autenticados (roles se añaden en commit 8)
                        .requestMatchers("/admin/**").authenticated()
                        // Todo lo demás: acceso libre
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        // Formulario por defecto de Spring por ahora (se personaliza en commit 10)
                        .defaultSuccessUrl("/", true)
                )
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf
                        // La consola H2 no envía CSRF: la excluimos
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        // Permite frames del mismo origen (necesario para la consola H2)
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}