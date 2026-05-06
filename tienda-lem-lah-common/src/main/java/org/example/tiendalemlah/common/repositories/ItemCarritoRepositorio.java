package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemCarritoRepositorio extends JpaRepository<ItemCarrito, Long> {

    List<ItemCarrito> findByUsuarioId(Long usuarioId);

    Optional<ItemCarrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioId(Long usuarioId);

    @Query("SELECT COUNT(DISTINCT i.producto.id) FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Long countProductosDistintos(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COALESCE(SUM(i.unidades), 0) FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Long sumUnidades(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COALESCE(SUM(i.unidades * i.producto.precio * (1 - i.producto.descuento / 100.0)), 0) " +
            "FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Double sumImporteTotal(@Param("usuarioId") Long usuarioId);
}