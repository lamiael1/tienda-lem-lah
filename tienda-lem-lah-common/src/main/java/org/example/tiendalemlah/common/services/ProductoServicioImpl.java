package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.repositories.ProductoRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepositorio repo;
    public ProductoServicioImpl(ProductoRepositorio repo){ this.repo = repo; }

    public List<Producto> findAll(){ return repo.findAll(); }
    public Optional<Producto> findById(Long id){ return repo.findById(id); }
    public Producto save(Producto p){ return repo.save(p); }
    public void deleteById(Long id){ repo.deleteById(id); }
}
