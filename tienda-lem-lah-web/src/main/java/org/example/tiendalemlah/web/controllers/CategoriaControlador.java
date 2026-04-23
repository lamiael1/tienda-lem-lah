package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final CategoriaServicio servicio;

    public CategoriaControlador(CategoriaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", servicio.findAll());
        return "categorias/lista";
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
