package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Producto;
import org.example.tiendalemlah.common.repositories.ProductoRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio repo;

    public ProductoServicioImpl(ProductoRepositorio repo) {
        this.repo = repo;
    }

    @Override
    public List<Producto> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Producto save(Producto p) {
        return repo.save(p);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    /**
     * Filtra los productos cuyas categorías tengan el tipo indicado.
     * Recorre todas las categorías de cada producto y comprueba si alguna
     * coincide con el tipo buscado (ignorando mayúsculas/minúsculas).
     */
    @Override
    public List<Producto> findByCategoriaTipo(String tipo) {
        return repo.findAll().stream()
                .filter(p -> p.getCategorias().stream()
                        .anyMatch(c -> c.getTipo().equalsIgnoreCase(tipo)))
                .toList();
    }
}