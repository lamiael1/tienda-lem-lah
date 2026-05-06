package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.entities.Usuario;
import org.example.tiendalemlah.common.services.UsuarioServicio;
import org.example.tiendalemlah.web.dto.RegistroDTO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/register")
public class RegistroControlador {

    private final UsuarioServicio usuarioServicio;
    private final PasswordEncoder passwordEncoder;

    public RegistroControlador(UsuarioServicio usuarioServicio,
                               PasswordEncoder passwordEncoder) {
        this.usuarioServicio = usuarioServicio;
        this.passwordEncoder = passwordEncoder;
    }

    /** Solo accesible si el usuario NO está autenticado */
    @GetMapping
    public String mostrarFormulario(Authentication auth, Model model) {
        if (estaAutenticado(auth)) return "redirect:/";
        model.addAttribute("registro", new RegistroDTO());
        return "register";
    }

    @PostMapping
    public String registrar(@ModelAttribute RegistroDTO registro,
                            Authentication auth,
                            Model model) {
        if (estaAutenticado(auth)) return "redirect:/";

        // Validación: checkbox de condiciones
        if (!registro.isAceptaCondiciones()) {
            model.addAttribute("error",
                    "Debes aceptar los términos y condiciones para registrarte.");
            model.addAttribute("registro", registro);
            return "register";
        }

        // Validación: email único
        if (usuarioServicio.existsByEmail(registro.getEmail())) {
            model.addAttribute("error",
                    "Ya existe una cuenta registrada con ese correo electrónico.");
            model.addAttribute("registro", registro);
            return "register";
        }

        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(registro.getNombre());
            usuario.setApellidos(registro.getApellidos());
            usuario.setEmail(registro.getEmail());
            usuario.setTelefono(registro.getTelefono());
            usuario.setPassword(passwordEncoder.encode(registro.getPassword()));
            usuario.setFechaRegistro(LocalDateTime.now());
            usuarioServicio.save(usuario);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la cuenta: " + e.getMessage());
            model.addAttribute("registro", registro);
            return "register";
        }
    }

    private boolean estaAutenticado(Authentication auth) {
        return auth != null
                && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);
    }
}