package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.repository.RutaRepositorio;
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
        return Optional.empty();
    }

    @Override
    public RutaDeTransporte consultarRutaDeTransporte(Long id) {
        return null;
    }

    @Override
    public void eliminarRutaDeTransporte(Long id) {

    }
}
