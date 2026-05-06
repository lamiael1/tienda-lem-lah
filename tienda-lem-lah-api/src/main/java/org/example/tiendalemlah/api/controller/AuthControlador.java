package org.example.tiendalemlah.api.controller;

import org.example.tiendalemlah.api.dto.*;
import org.example.tiendalemlah.api.security.JwtServicio;
import org.example.tiendalemlah.security.UsuarioDetallesServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControlador {

    private final AuthenticationManager authManager;
    private final UsuarioDetallesServicio userDetailsService;
    private final JwtServicio jwtServicio;

    public AuthControlador(AuthenticationManager authManager,
                           UsuarioDetallesServicio userDetailsService,
                           JwtServicio jwtServicio) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtServicio = jwtServicio;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        return ResponseEntity.ok(new LoginResponseDTO(
                jwtServicio.generateAccessToken(user),
                jwtServicio.generateRefreshToken(user)
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequestDTO request) {
        try {
            String username = jwtServicio.extractUsername(request.getRefreshToken());
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (!jwtServicio.isTokenValid(request.getRefreshToken(), user)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(new RefreshResponseDTO(jwtServicio.generateAccessToken(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}