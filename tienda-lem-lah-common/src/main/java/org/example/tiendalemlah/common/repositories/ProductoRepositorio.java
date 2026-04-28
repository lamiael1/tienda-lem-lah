package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.categorias LEFT JOIN FETCH p.marca WHERE p.id = :id")
    Optional<Producto> findByIdWithCategorias(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Producto p JOIN FETCH p.categorias c JOIN FETCH p.marca WHERE c.tipo = :tipo")
    List<Producto> findByCategoriaTipoFetch(@Param("tipo") String tipo);
}