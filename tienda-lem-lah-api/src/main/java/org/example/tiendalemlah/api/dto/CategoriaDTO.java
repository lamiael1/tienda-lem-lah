package org.example.tiendalemlah.api.dto;

import lombok.Data;

@Data
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String tipo;
}