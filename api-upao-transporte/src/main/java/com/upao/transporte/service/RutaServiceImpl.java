package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.repository.RutaRepositorio;

import java.util.List;

public class RutaServiceImpl {

    private final RutaRepositorio rutaRepository;

    public RutaServiceImpl(RutaRepositorio rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    //Buscar rutas por origen y destino
    public List<RutaDeTransporte> buscarRutas(String origen, String destino) {
        return rutaRepository.findByOrigenAndDestino(origen, destino);
    }
}
