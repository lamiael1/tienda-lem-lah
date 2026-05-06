package org.example.tiendalemlah.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class CarritoDTO {
    private List<ItemCarritoDTO> items;
    private Long numProductosDistintos;
    private Long numUnidadesTotales;
    private Double importeTotal;
}