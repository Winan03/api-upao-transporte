package com.upao.transporte.repository;

import com.upao.transporte.entity.RutaDeTransporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRepositorio extends JpaRepository <RutaDeTransporte, Long> {
    List<RutaDeTransporte> findByOrigenAndDestino(String origen, String destino);
}
