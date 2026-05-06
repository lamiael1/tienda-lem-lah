package org.example.tiendalemlah.api.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}