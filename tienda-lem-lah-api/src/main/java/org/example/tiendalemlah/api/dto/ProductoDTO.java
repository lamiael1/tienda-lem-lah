package org.example.tiendalemlah.api.dto;

import lombok.Data;
import java.util.Set;

@Data
public class ProductoDTO {
    private Long id;
    private String codigoEan;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer descuento;
    private MarcaDTO marca;
    private Set<CategoriaDTO> categorias;
}