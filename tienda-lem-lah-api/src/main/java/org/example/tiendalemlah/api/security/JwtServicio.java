package org.example.tiendalemlah.api.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServicio {
    String generateAccessToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String extractUsername(String token);
    Boolean isTokenExpired(String token);
    Boolean isTokenValid(String token, UserDetails user);
}