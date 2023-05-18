package com.upao.transporte.service;

import com.upao.transporte.entity.Tarjeta;
import com.upao.transporte.entity.Usuario;

import java.util.Optional;

public interface TarjetaService {
    Tarjeta createTarjeta(Tarjeta tarjeta);
    Tarjeta modificarTarjeta(Tarjeta tarjeta);
    Optional<Tarjeta> findById(Long id);
    void eliminarTarjeta(Long id);
    Tarjeta consultarInformacionTarjeta(Long id);
    String obtenerSaldo(Tarjeta tarjeta);
}
