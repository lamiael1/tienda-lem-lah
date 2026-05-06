package org.example.tiendalemlah.api.mapper;

import org.example.tiendalemlah.api.dto.ProductoDTO;
import org.example.tiendalemlah.common.entities.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDTO toDTO(Producto producto);
    List<ProductoDTO> toDTOList(List<Producto> productos);
}