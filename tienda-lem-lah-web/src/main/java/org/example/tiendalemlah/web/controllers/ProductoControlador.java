

package org.example.tiendalemlah.web.controllers;

import org.example.tiendalemlah.common.services.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/productos")

    public class ProductoControlador {

        private final ProductoServicio servicio;

        public ProductoControlador(ProductoServicio servicio) {
            this.servicio = servicio;
        }

        // Redirige /productos/ (con barra) a /productos (sin barra)
        @GetMapping("/")
        public String listarSlash() {
            return "redirect:/productos";
        }

        @GetMapping
        public String listar(Model model) {
            model.addAttribute("productos", servicio.findAll());
            return "productos/lista";
        }

        /**
         * Detalle de producto.
         * La URL es /productos/{id}/{slug} donde {slug} es el nombre escapado (SEO-friendly).
         * El slug se declara como @PathVariable pero se ignora: solo sirve para la URL amigable.
         * Se usa @PathVariable String slug (ignorado) en vez de no declararlo, para que Spring
         * no rechace la URL por tener un segmento extra sin mapear.
         */
        @GetMapping("/{id}/{slug}")
        public String detalle(@PathVariable Long id,
                              @PathVariable String slug,  // recibido pero ignorado intencionalmente
                              Model model) {
            return servicio.findById(id)
                    .map(p -> {
                        model.addAttribute("producto", p);
                        return "productos/detalle";
                    })
                    .orElse("redirect:/productos");
        }
    }
