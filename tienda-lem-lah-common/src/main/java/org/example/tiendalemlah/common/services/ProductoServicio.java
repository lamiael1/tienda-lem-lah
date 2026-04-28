package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoServicio {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Optional<Producto> findByIdWithCategorias(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    List<Producto> findByCategoriaTipo(String tipo);
}