package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    public CategoriaControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @GetMapping({"", "/"})
    public String listaCategorias(
            @RequestParam(value = "tipo", required = false) String tipo,
            Model model) {

        if (tipo == null) {
            tipo = "perros"; // valor por defecto
        }

        model.addAttribute("tipo", tipo);
        model.addAttribute("categorias", categoriaServicio.findByTipo(tipo));

        return "categorias/lista";
    }

    @GetMapping("/{id}")
    public String categoryDetail(
            @PathVariable("id") Long id,
            Model model) {

        return categoriaServicio.findById(id)
                .map(categoria -> {
                    model.addAttribute("categoria", categoria);
                    return "categorias/detalle";
                })
                .orElse("redirect:/categorias");
    }

}

