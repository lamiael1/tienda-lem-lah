package org.example.tiendalemlah.security;

import org.example.tiendalemlah.common.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Adaptador entre la entidad Usuario (módulo common, sin Spring Security)
 * y la interfaz UserDetails (Spring Security).
 * Así common NO depende de Spring Security.
 */
public class UsuarioDetalles implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetalles(Usuario usuario) {
        this.usuario = usuario;
    }

    // Métodos de conveniencia para usar en controladores
    public Long getId()               { return usuario.getId(); }
    public String getNombreCompleto() { return usuario.getNombre() + " " + usuario.getApellidos(); }
    public String getEmail()          { return usuario.getEmail(); }
    public Usuario getUsuario()       { return usuario; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getId()))
                .collect(Collectors.toSet());
    }

    @Override public String getPassword()              { return usuario.getPassword(); }
    @Override public String getUsername()              { return usuario.getEmail(); }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }
}