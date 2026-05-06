package org.example.tiendalemlah.api.dto;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private String nombreProducto;
    private Double precioUnitario;
    private Integer descuento;
    private Double precioConDescuento;
    private Integer unidades;
    private Double precioTotal;
}
