package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.MarcaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/marcas")
public class MarcaControlador {

    private final MarcaServicio marcaServicio;

    public MarcaControlador(MarcaServicio marcaServicio) {
        this.marcaServicio = marcaServicio;
    }

    /**
     * Detalle de una marca con sus productos ordenados alfabéticamente.
     * Los productos se ordenan usando Comparator (expresión funcional), NO con JPA.
     */
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return marcaServicio.findById(id)
                .map(marca -> {
                    model.addAttribute("marca", marca);
                    // Ordenar alfabéticamente por nombre con Comparator funcional
                    var productosOrdenados = marca.getProductos().stream()
                            .sorted(Comparator.comparing(p -> p.getNombre().toLowerCase()))
                            .collect(Collectors.toList());
                    model.addAttribute("productosOrdenados", productosOrdenados);
                    return "marcas/detalle";
                })
                .orElse("redirect:/productos");
    }
}