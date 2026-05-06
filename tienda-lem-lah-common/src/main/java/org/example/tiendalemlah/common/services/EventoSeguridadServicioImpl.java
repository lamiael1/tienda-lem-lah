package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.EventoSeguridad;
import org.example.tiendalemlah.common.entities.TipoEvento;
import org.example.tiendalemlah.common.repositories.EventoSeguridadRepositorio;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EventoSeguridadServicioImpl implements EventoSeguridadServicio {

    private final EventoSeguridadRepositorio repo;

    public EventoSeguridadServicioImpl(EventoSeguridadRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public void registrar(String nombreUsuario, TipoEvento tipo) {
        EventoSeguridad evento = new EventoSeguridad();
        evento.setFechaHora(LocalDateTime.now());
        evento.setNombreUsuario(nombreUsuario);
        evento.setTipo(tipo);
        repo.save(evento);
    }
}