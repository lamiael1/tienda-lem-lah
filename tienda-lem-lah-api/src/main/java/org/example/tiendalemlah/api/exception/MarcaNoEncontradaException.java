package org.example.tiendalemlah.api.exception;

public class MarcaNoEncontradaException extends RuntimeException {
    public MarcaNoEncontradaException(Long id) {
        super("Marca no encontrada con id: " + id);
    }
}