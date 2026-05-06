package org.example.tiendalemlah.api.controller;

import org.example.tiendalemlah.api.dto.AnadirAlCarritoDTO;
import org.example.tiendalemlah.api.dto.CarritoDTO;
import org.example.tiendalemlah.api.dto.ItemCarritoDTO;
import org.example.tiendalemlah.api.exception.*;
import org.example.tiendalemlah.common.entities.ItemCarrito;
import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.repositories.ItemCarritoRepositorio;
import org.example.tiendalemlah.common.repositories.ProductoRepositorio;
import org.example.tiendalemlah.security.UsuarioDetalles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CarritoControlador {

    private final ItemCarritoRepositorio carritoRepo;
    private final ProductoRepositorio productoRepo;

    public CarritoControlador(ItemCarritoRepositorio carritoRepo,
                              ProductoRepositorio productoRepo) {
        this.carritoRepo = carritoRepo;
        this.productoRepo = productoRepo;
    }

    // POST /api/v1/cart
    @PostMapping
    @Transactional
    public ResponseEntity<CarritoDTO> añadir(@RequestBody AnadirAlCarritoDTO dto,
                                             @AuthenticationPrincipal UsuarioDetalles usuarioDetalles) {
        if (dto.getUnidades() == null || dto.getUnidades() <= 0) {
            throw new UnidadesInvalidasException();
        }

        Producto producto = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException(dto.getProductoId()));

        Optional<ItemCarrito> existente = carritoRepo.findByUsuarioIdAndProductoId(
                usuarioDetalles.getId(), dto.getProductoId());

        int unidadesEnCarrito = existente.map(ItemCarrito::getUnidades).orElse(0);
        int totalSolicitado = unidadesEnCarrito + dto.getUnidades();

        if (producto.getStock() < totalSolicitado) {
            throw new StockInsuficienteException(dto.getProductoId(), producto.getStock(), totalSolicitado);
        }

        if (existente.isPresent()) {
            existente.get().setUnidades(totalSolicitado);
            carritoRepo.save(existente.get());
        } else {
            ItemCarrito item = new ItemCarrito();
            item.setUsuario(usuarioDetalles.getUsuario());
            item.setProducto(producto);
            item.setUnidades(dto.getUnidades());
            carritoRepo.save(item);
        }

        return ResponseEntity.ok(buildCarritoDTO(usuarioDetalles.getId()));
    }

    // GET /api/v1/cart
    @GetMapping
    public ResponseEntity<CarritoDTO> listar(@AuthenticationPrincipal UsuarioDetalles usuarioDetalles) {
        return ResponseEntity.ok(buildCarritoDTO(usuarioDetalles.getId()));
    }

    // DELETE /api/v1/cart/{productId}
    @DeleteMapping("/{productId}")
    @Transactional
    public ResponseEntity<CarritoDTO> eliminarProducto(@PathVariable Long productId,
                                                       @AuthenticationPrincipal UsuarioDetalles usuarioDetalles) {
        productoRepo.findById(productId)
                .orElseThrow(() -> new ProductoNoEncontradoException(productId));

        carritoRepo.findByUsuarioIdAndProductoId(usuarioDetalles.getId(), productId)
                .orElseThrow(() -> new ProductoNoEnCarritoException(productId));

        carritoRepo.deleteByUsuarioIdAndProductoId(usuarioDetalles.getId(), productId);

        return ResponseEntity.ok(buildCarritoDTO(usuarioDetalles.getId()));
    }

    // DELETE /api/v1/cart
    @DeleteMapping
    @Transactional
    public ResponseEntity<CarritoDTO> vaciar(@AuthenticationPrincipal UsuarioDetalles usuarioDetalles) {
        carritoRepo.deleteByUsuarioId(usuarioDetalles.getId());
        return ResponseEntity.ok(buildCarritoDTO(usuarioDetalles.getId()));
    }

    // ── Helper ──────────────────────────────────────────────────────────────
    private CarritoDTO buildCarritoDTO(Long usuarioId) {
        List<ItemCarrito> items = carritoRepo.findByUsuarioId(usuarioId);

        List<ItemCarritoDTO> itemDTOs = items.stream().map(item -> {
            ItemCarritoDTO d = new ItemCarritoDTO();
            Producto p = item.getProducto();
            d.setNombreProducto(p.getNombre());
            d.setPrecioUnitario(p.getPrecio());
            d.setDescuento(p.getDescuento());
            double precioConDto = p.getPrecio() * (1 - p.getDescuento() / 100.0);
            d.setPrecioConDescuento(precioConDto);
            d.setUnidades(item.getUnidades());
            d.setPrecioTotal(precioConDto * item.getUnidades());
            return d;
        }).toList();

        CarritoDTO carrito = new CarritoDTO();
        carrito.setItems(itemDTOs);
        carrito.setNumProductosDistintos(carritoRepo.countProductosDistintos(usuarioId));
        carrito.setNumUnidadesTotales(carritoRepo.sumUnidades(usuarioId));
        carrito.setImporteTotal(carritoRepo.sumImporteTotal(usuarioId));
        return carrito;
    }
}