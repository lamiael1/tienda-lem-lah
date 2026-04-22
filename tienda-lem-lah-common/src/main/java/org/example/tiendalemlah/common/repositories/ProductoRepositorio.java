package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {}

