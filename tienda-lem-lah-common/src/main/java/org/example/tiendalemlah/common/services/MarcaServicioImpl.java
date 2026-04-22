package org.example.tiendalemlah.common.services;

import org.example.tiendalemlah.common.entities.Marca;
import org.example.tiendalemlah.common.repositories.MarcaRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaServicioImpl implements MarcaServicio {

    private final MarcaRepositorio marcaRepositorio;

    public MarcaServicioImpl(MarcaRepositorio marcaRepositorio) {
        this.marcaRepositorio = marcaRepositorio;
    }

    @Override
    public List<Marca> findAll() {
        return marcaRepositorio.findAll();
    }

    @Override
    public Optional<Marca> findById(Long id) {
        return marcaRepositorio.findById(id);
    }

    @Override
    public Marca save(Marca marca) {
        return marcaRepositorio.save(marca);
    }

    @Override
    public void deleteById(Long id) {
        marcaRepositorio.deleteById(id);
    }
}
