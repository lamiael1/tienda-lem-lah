package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.Categoria;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTipoIgnoreCase(String tipo);
    List<Categoria> findAll(Sort sort);
}
