package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.Categoria;

import java.util.List;
import java.util.Optional;


public class CategoriaRepositorio {


    public interface CategoryRepository {

        List<Categoria> findAll();

        Optional<Categoria> findById(Long id);

        Categoria save(Categoria categoria);

        void deleteById(Long id);
    }

}

