package org.example.tiendalemlah.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminInicioControlador {

    @GetMapping
    public String adminHome() {
        return "admin/index";
    }

    // Redirige /admin/ (con barra) a /admin (sin barra)
    @GetMapping("/")
    public String adminHomeSlash() {
        return "redirect:/admin";
    }
}