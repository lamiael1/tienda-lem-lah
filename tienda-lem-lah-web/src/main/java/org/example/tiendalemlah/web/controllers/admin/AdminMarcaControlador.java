package org.example.tiendalemlah.web.controllers.admin;

import org.example.tiendalemlah.common.services.MarcaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/marcas")
public class AdminMarcaControlador {

    private final MarcaServicio servicio;

    public AdminMarcaControlador(MarcaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", servicio.findAll());
        return "admin/marcas/listado";
    }
}
