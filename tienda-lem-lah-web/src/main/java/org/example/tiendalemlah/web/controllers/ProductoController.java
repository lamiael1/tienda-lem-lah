package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio servicio;
    public ProductoController(ProductoServicio servicio){ this.servicio = servicio; }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", servicio.findAll());
        return "productos/lista";
    }
    @GetMapping("/{id}/{slug}")
    public String detalle(@PathVariable Long id, Model model){
        return servicio.findById(id)
                .map(p -> { model.addAttribute("producto", p); return "productos/detalle"; })
                .orElse("redirect:/productos");
    }


}
