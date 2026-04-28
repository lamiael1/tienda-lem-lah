package org.example.tiendalemlah.web.controllers.admin;

import org.example.tiendalemlah.common.entities.Categoria;
import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.services.CategoriaServicio;
import org.example.tiendalemlah.common.services.MarcaServicio;
import org.example.tiendalemlah.common.services.ProductoServicio;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Redirige /admin/productos/ (con barra) a /admin/productos
    @GetMapping("/")
    public String listarSlash() {
        return "redirect:/admin/productos";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "admin/productos/lista"; // ✅ corregido (antes devolvía "lista")
    }

    @GetMapping("/new")
    public String crearForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("marcas", marcaServicio.findAll());
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "admin/productos/crear";
    }

    /**
     * Guardado de nuevo producto.
     * La marca y categorías se reciben por ID separado para evitar problemas de binding JPA.
     */
    @PostMapping
    public String guardar(@ModelAttribute Producto producto,
                          @RequestParam Long marcaId,
                          @RequestParam(required = false) List<Long> categoriaIds) {

        producto.setMarca(marcaServicio.findById(marcaId).orElseThrow());

        Set<Categoria> cats = new HashSet<>();
        if (categoriaIds != null) {
            categoriaIds.forEach(cid ->
                    categoriaServicio.findById(cid).ifPresent(cats::add));
        }
        producto.setCategorias(cats);

        productoServicio.save(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/{id}/edit")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.findByIdWithCategorias(id).orElse(null));
        model.addAttribute("marcas", marcaServicio.findAll());
        model.addAttribute("categorias", categoriaServicio.findAll());
        return "admin/productos/editar";
    }
    @PostMapping("/{id}/edit")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Producto producto,
                             @RequestParam Long marcaId,
                             @RequestParam(required = false) List<Long> categoriaIds,
                             Model model) {

        // Carga el producto existente de la BD
        Producto existente = productoServicio.findById(id).orElseThrow();

        // Actualiza solo los campos del formulario
        existente.setCodigoEan(producto.getCodigoEan());
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setImagen(producto.getImagen());
        existente.setPrecio(producto.getPrecio());
        existente.setDescuento(producto.getDescuento());
        existente.setMarca(marcaServicio.findById(marcaId).orElseThrow());

        Set<Categoria> cats = new HashSet<>();
        if (categoriaIds != null) {
            categoriaIds.forEach(cid ->
                    categoriaServicio.findById(cid).ifPresent(cats::add));
        }
        existente.setCategorias(cats);

        productoServicio.save(existente);
        return "redirect:/admin/productos";
    }

    @GetMapping("/{id}/delete")
    public String eliminarForm(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoServicio.findById(id).orElse(null));
        return "admin/productos/eliminar";
    }

    @PostMapping("/{id}/delete")
    public String eliminar(@PathVariable Long id) {
        productoServicio.deleteById(id);
        return "redirect:/admin/productos";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }
}