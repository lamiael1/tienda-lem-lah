package org.example.tiendalemlah.common.repositories;

import org.example.tiendalemlah.common.entities.Categoria;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación en memoria usando Map, como indica el enunciado.
 */
@Repository
public class MemoriaCategoriaRepositorio implements CategoriaRepositorio {

    private final Map<Long, Categoria> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public List<Categoria> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Categoria save(Categoria category) {
        if (category.getId() == null) {
            category.setId(sequence.incrementAndGet());
        }
        data.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }
}
