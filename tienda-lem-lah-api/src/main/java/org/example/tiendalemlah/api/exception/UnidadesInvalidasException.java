package org.example.tiendalemlah.api.exception;

public class UnidadesInvalidasException extends RuntimeException {
    public UnidadesInvalidasException() {
        super("Las unidades deben ser mayor que cero");
    }

    public static class CategoriaNoEncontradaException extends RuntimeException {
        public CategoriaNoEncontradaException(Long id) {
            super("Categoría no encontrada con id: " + id);
        }
    }
}