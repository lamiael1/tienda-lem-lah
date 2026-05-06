package org.example.tiendalemlah.api.controller;

import org.example.tiendalemlah.api.dto.ProductoDTO;
import org.example.tiendalemlah.api.mapper.ProductoMapper;
import org.example.tiendalemlah.common.repositories.ProductoRepositorio;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductoApiControlador {

    private final ProductoRepositorio productoRepositorio;
    private final ProductoMapper productoMapper;

    public ProductoApiControlador(ProductoRepositorio productoRepositorio,
                                  ProductoMapper productoMapper) {
        this.productoRepositorio = productoRepositorio;
        this.productoMapper = productoMapper;
    }

    // GET /api/v1/products
    @GetMapping("/products")
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> productos = productoMapper.toDTOList(
                productoRepositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"))
        );
        return ResponseEntity.ok(productos);
    }

    // GET /api/v1/categories/{categoryId}/products
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductoDTO>> listarPorCategoria(@PathVariable Long categoryId) {
        List<ProductoDTO> productos = productoMapper.toDTOList(
                productoRepositorio.findByCategoriaId(
                        categoryId,
                        Sort.by(Sort.Direction.ASC, "nombre")
                )
        );
        return ResponseEntity.ok(productos);
    }
}