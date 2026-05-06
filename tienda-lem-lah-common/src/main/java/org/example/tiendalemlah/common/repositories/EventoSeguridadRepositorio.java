package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.EventoSeguridad;
import org.example.tiendalemlah.common.entities.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoSeguridadRepositorio extends JpaRepository<EventoSeguridad, Long> {
    List<EventoSeguridad> findByNombreUsuarioOrderByFechaHoraDesc(String nombreUsuario);
    List<EventoSeguridad> findByTipoOrderByFechaHoraDesc(TipoEvento tipo);
}