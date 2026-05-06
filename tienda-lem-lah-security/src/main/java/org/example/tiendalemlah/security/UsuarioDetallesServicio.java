package org.example.tiendalemlah.security;

import org.example.tiendalemlah.common.services.UsuarioServicio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioServicio usuarioServicio;

    public UsuarioDetallesServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioServicio.findByEmail(email)
                .map(UsuarioDetalles::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No existe usuario con email: " + email));
    }
}