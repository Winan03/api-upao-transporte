package com.upao.transporte.repository;

import com.upao.transporte.entity.RutaDeTransporte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutaRepositorio extends JpaRepository <RutaDeTransporte, String> {

    Optional<RutaDeTransporte> findByOrigen(String origen);
    Optional<RutaDeTransporte> findByDestino(String destino);

}
