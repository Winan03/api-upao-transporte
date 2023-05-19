package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;

import java.util.Optional;

public interface RutaService {

    RutaDeTransporte createRutaDeTransporte (RutaDeTransporte ruta);
    RutaDeTransporte modificarRutaDeTransporte(RutaDeTransporte ruta);
    Optional<RutaDeTransporte> findByOrigen(String origen);
    Optional<RutaDeTransporte> findByDestino(String destino);
    RutaDeTransporte consultarRutaDeTransporte(Long id);
    void eliminarRutaDeTransporte(Long id);

}
