package org.example.tiendalemlah.api.exception;

public class ProductoNoEnCarritoException extends RuntimeException {
    public ProductoNoEnCarritoException(Long productoId) {
        super("El producto " + productoId + " no está en el carrito");
    }
}