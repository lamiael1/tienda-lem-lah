package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.repositories.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepositorio categoriaRepositorio;

    public CategoriaServicioImpl(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepositorio.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepositorio.findById(id);
    }

    @Override
    public Categoria save(Categoria category) {
        return categoriaRepositorio.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepositorio.deleteById(id);
    }
}
