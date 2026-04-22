package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Marca;
import java.util.List;
import java.util.Optional;

public interface MarcaServicio {
    List<Marca> findAll();
    Optional<Marca> findById(Long id);
    Marca save(Marca marca);
    void deleteById(Long id);
}
