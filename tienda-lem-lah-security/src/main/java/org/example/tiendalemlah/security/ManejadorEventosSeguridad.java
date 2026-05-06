package org.example.tiendalemlah.security;

import org.example.tiendalemlah.common.entities.TipoEvento;
import org.example.tiendalemlah.common.services.EventoSeguridadServicio;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Escucha los eventos de autenticación que publica Spring Security
 * automáticamente y los persiste en la tabla eventos_seguridad.
 */
@Component
public class ManejadorEventosSeguridad {

    private final EventoSeguridadServicio eventoSeguridadServicio;

    public ManejadorEventosSeguridad(EventoSeguridadServicio eventoSeguridadServicio) {
        this.eventoSeguridadServicio = eventoSeguridadServicio;
    }

    /** Se dispara cuando un usuario completa el login con éxito */
    @EventListener
    public void onLoginExitoso(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        eventoSeguridadServicio.registrar(username, TipoEvento.LOGIN_EXITOSO);
    }

    /** Se dispara cuando un usuario falla el login */
    @EventListener
    public void onLoginFallido(AbstractAuthenticationFailureEvent event) {
        String username = event.getAuthentication().getName();
        eventoSeguridadServicio.registrar(username, TipoEvento.LOGIN_FALLIDO);
    }
}