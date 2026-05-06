package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Usuario;
import org.example.tiendalemlah.common.repositories.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio repo;

    public UsuarioServicioImpl(UsuarioRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}