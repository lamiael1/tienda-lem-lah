package org.example.tiendalemlah.web.controllers.admin;

import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categorias")
public class AdminCategoriaControlador {

    private final CategoriaServicio servicio;

    public AdminCategoriaControlador   (CategoriaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", servicio.findAll());
        return "admin/categorias/listado";
    }
}

