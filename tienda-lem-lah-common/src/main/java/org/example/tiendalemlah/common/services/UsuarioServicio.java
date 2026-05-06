package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Usuario;
import java.util.Optional;

public interface UsuarioServicio {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    boolean existsByEmail(String email);
}