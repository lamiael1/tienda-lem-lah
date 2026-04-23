package org.example.tiendalemlah.web.controllers.admin;

import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.example.tiendalemlah.common.services.MarcaServicio;
import org.example.tiendalemlah.common.services.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoControlador {

    private final ProductoServicio productoServicio;
    private final MarcaServicio marcaServicio;
    private final CategoriaServicio categoriaServicio;

    public AdminProductoControlador(ProductoServicio productoServicio,
                                   MarcaServicio marcaServicio,
                                   CategoriaServicio categoriaServicio) {
        this.productoServicio = productoServicio;
        this.marcaServicio = marcaServicio;
        this.categoriaServicio = categoriaServicio;
    }

    // LISTADO
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "lista";
    }

    // FORMULARIO CREAR
    @GetMapping("/new")
    public String crearForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("marcas", marcaServicio.findAll());
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "admin/productos/crear";
    }

    // GUARDAR NUEVO
    @PostMapping
    public String guardar(@ModelAttribute Producto producto) {
        productoServicio.save(producto);
        return "redirect:/admin/productos";
    }

    // FORMULARIO EDITAR
    @GetMapping("/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.findById(id).orElse(null));
        model.addAttribute("marcas", marcaServicio.findAll());
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "admin/productos/editar";
    }

    // ACTUALIZAR
    @PostMapping("/{id}/edit")
    public String actualizar(@PathVariable Long id, @ModelAttribute Producto producto) {
        producto.setId(id);
        productoServicio.save(producto);
        return "redirect:/admin/productos";
    }

    // FORMULARIO ELIMINAR
    @GetMapping("/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.findById(id).orElse(null));
        return "admin/productos/eliminar";
    }

    // ELIMINAR
    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        productoServicio.deleteById(id);
        return "redirect:/admin/productos";
    }
}