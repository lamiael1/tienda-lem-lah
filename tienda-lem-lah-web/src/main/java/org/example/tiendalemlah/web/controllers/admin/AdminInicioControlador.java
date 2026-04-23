package org.example.tiendalemlah.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminInicioControlador {

    @GetMapping
    public String adminHome() {
        // Página principal del área de administración
        return "admin/index";
    }
}
