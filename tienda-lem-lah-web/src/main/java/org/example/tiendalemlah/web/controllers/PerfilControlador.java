package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.UsuarioServicio;
import org.example.tiendalemlah.security.UsuarioDetalles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class PerfilControlador {

    private final UsuarioServicio usuarioServicio;

    public PerfilControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Muestra el perfil del usuario autenticado.
     * URL: /users/profile
     */
    @GetMapping("/profile")
    public String miPerfil(@AuthenticationPrincipal UsuarioDetalles detalles,
                           Model model) {
        model.addAttribute("usuario", detalles.getUsuario());
        return "users/profile";
    }

    /**
     * Muestra el perfil del usuario con el id indicado.
     * URL: /users/profile/{userId}
     *
     * Solo pueden acceder:
     * - Usuarios con rol ADMIN
     * - El propio usuario cuyo id coincide con el parámetro
     */
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public String perfilPorId(@PathVariable Long userId, Model model) {
        return usuarioServicio.findById(userId)
                .map(u -> {
                    model.addAttribute("usuario", u);
                    return "users/profile";
                })
                .orElse("redirect:/");
    }
}