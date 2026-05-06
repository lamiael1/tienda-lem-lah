package org.example.tiendalemlah.api.mapper;

import org.example.tiendalemlah.api.dto.CategoriaDTO;
import org.example.tiendalemlah.common.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "productos", ignore = true)
    CategoriaDTO toDTO(Categoria categoria);

    List<CategoriaDTO> toDTOList(List<Categoria> categorias);
}
