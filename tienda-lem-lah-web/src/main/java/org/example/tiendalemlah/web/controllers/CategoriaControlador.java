package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.example.tiendalemlah.common.services.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final CategoriaServicio servicio;
    private final ProductoServicio productoServicio;

    public CategoriaControlador(CategoriaServicio servicio, ProductoServicio productoServicio) {
        this.servicio = servicio;
        this.productoServicio = productoServicio;
    }

    // LISTADO filtrado por tipo (perros / gatos)
    @GetMapping
    public String listar(@RequestParam(required = false) String tipo, Model model) {
        if (tipo != null && !tipo.isBlank()) {
            model.addAttribute("categorias", servicio.findByTipo(tipo));
            model.addAttribute("productos", productoServicio.findByCategoriaTipo(tipo));
        } else {
            model.addAttribute("categorias", servicio.findAll());
            model.addAttribute("productos", productoServicio.findAll());
        }
        model.addAttribute("tipo", tipo);
        return "categorias/lista";
    }

    // DETALLE de una categoría
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return servicio.findById(id)
                .map(c -> { model.addAttribute("categoria", c); return "categorias/detalle"; })
                .orElse("redirect:/categorias");
    }

    @GetMapping("/new")
    public String crearForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Categoria categoria) {
        servicio.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", servicio.findById(id).orElse(null));
        return "categorias/editar";
    }

    @PostMapping("/{id}/edit")
    public String actualizar(@PathVariable Long id, @ModelAttribute Categoria categoria) {
        categoria.setId(id);
        servicio.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", servicio.findById(id).orElse(null));
        return "categorias/eliminar";
    }

    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        servicio.deleteById(id);
        return "redirect:/categorias";
    }
}