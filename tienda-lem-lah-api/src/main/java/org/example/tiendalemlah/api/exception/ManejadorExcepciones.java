package org.example.tiendalemlah.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ProblemDetail handleProductoNoEncontrado(ProductoNoEncontradoException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Producto no encontrado");
        return pd;
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ProblemDetail handleStockInsuficiente(StockInsuficienteException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        pd.setTitle("Stock insuficiente");
        return pd;
    }

    @ExceptionHandler(ProductoNoEnCarritoException.class)
    public ProblemDetail handleProductoNoEnCarrito(ProductoNoEnCarritoException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        pd.setTitle("Producto no en carrito");
        return pd;
    }

    @ExceptionHandler(UnidadesInvalidasException.class)
    public ProblemDetail handleUnidadesInvalidas(UnidadesInvalidasException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Unidades inválidas");
        return pd;
    }
    @ExceptionHandler(MarcaNoEncontradaException.class)
    public ProblemDetail handleMarcaNoEncontrada(MarcaNoEncontradaException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Marca no encontrada");
        return pd;
    }

    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ProblemDetail handleCategoriaNoEncontrada(CategoriaNoEncontradaException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Categoría no encontrada");
        return pd;
    }
}