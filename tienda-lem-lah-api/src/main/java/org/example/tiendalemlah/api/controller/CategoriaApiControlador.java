package org.example.tiendalemlah.api.controller;

import org.example.tiendalemlah.api.dto.CategoriaDTO;
import org.example.tiendalemlah.api.mapper.CategoriaMapper;
import org.example.tiendalemlah.common.repositories.CategoriaRepositorio;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriaApiControlador {

    private final CategoriaRepositorio categoriaRepositorio;
    private final CategoriaMapper categoriaMapper;

    public CategoriaApiControlador(CategoriaRepositorio categoriaRepositorio,
                                   CategoriaMapper categoriaMapper) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.categoriaMapper = categoriaMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<CategoriaDTO> categorias = categoriaMapper.toDTOList(
                categoriaRepositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"))
        );
        return ResponseEntity.ok(categorias);
    }
}