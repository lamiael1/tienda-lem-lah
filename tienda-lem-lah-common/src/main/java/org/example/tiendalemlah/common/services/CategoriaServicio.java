package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Categoria;

import java.util.List;
import java.util.Optional;


public interface CategoriaServicio {

        List<Categoria> findAll();
        List<Categoria> findByTipo(String tipo);

        Optional<Categoria> findById(Long id);

        Categoria save(Categoria category);

        void deleteById(Long id);
    }


