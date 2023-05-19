package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.repository.RutaRepositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RutaServiceImpl implements RutaService {
    private final RutaRepositorio rutaRepositorio;

    public RutaServiceImpl(RutaRepositorio rutaRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
    }

    @Override
    public RutaDeTransporte createRutaDeTransporte(RutaDeTransporte ruta) {
        return null;
    }

    @Override
    public RutaDeTransporte modificarRutaDeTransporte(RutaDeTransporte ruta) {
        return null;
    }

    @Override
    public Optional<RutaDeTransporte> findByOrigen(String origen) {
        return rutaRepositorio.findByOrigen(origen);
    }

    @Override
    public Optional<RutaDeTransporte> findByDestino(String destino) {
        return rutaRepositorio.findByDestino(destino);
    }

    @Override
    public RutaDeTransporte consultarRutaDeTransporte(Long id) {
        return null;
    }

    @Override
    public void eliminarRutaDeTransporte(Long id) {
        rutaRepositorio.deleteById(String.valueOf(id));
    }

}
