package org.example.tiendalemlah.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tiendalemlah.common.entities.TipoEvento;
import org.example.tiendalemlah.common.services.EventoSeguridadServicio;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ManejadorLogout implements LogoutSuccessHandler {

    private final EventoSeguridadServicio eventoSeguridadServicio;

    public ManejadorLogout(EventoSeguridadServicio eventoSeguridadServicio) {
        this.eventoSeguridadServicio = eventoSeguridadServicio;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        if (authentication != null) {
            eventoSeguridadServicio.registrar(
                    authentication.getName(), TipoEvento.LOGOUT);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}