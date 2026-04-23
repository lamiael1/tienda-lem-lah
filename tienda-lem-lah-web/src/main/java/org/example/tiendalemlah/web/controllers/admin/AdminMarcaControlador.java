package org.example.tiendalemlah.web.controllers.admin;

import org.example.tiendalemlah.common.entities.Marca;
import org.example.tiendalemlah.common.services.MarcaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/marcas")
public class AdminMarcaControlador {

    private final MarcaServicio marcaServicio;

    public AdminMarcaControlador(MarcaServicio marcaServicio) {
        this.marcaServicio = marcaServicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaServicio.findAll());
        return "lista";
    }

    @GetMapping("/new")
    public String crearForm(Model model) {
        model.addAttribute("marca", new Marca());   // 👈 IMPORTANTE: "marca"
        return "admin/marcas/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Marca marca) {
        marcaServicio.save(marca);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaServicio.findById(id).orElse(null)); // 👈 "marca"
        return "admin/marcas/editar";
    }

    @PostMapping("/{id}/edit")
    public String actualizar(@PathVariable Long id, @ModelAttribute Marca marca) {
        marca.setId(id);
        marcaServicio.save(marca);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaServicio.findById(id).orElse(null));
        return "admin/marcas/eliminar";
    }

    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        marcaServicio.deleteById(id);
        return "redirect:/admin/marcas";
    }
}