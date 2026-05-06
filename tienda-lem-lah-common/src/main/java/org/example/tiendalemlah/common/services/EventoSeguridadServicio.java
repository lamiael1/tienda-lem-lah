package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.TipoEvento;

public interface EventoSeguridadServicio {
    void registrar(String nombreUsuario, TipoEvento tipo);
}