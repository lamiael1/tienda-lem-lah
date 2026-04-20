package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    public CategoriaControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @GetMapping({"", "/"})
    public String listaCategorias(Model model) {
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "categorias/lista";
    }
}

