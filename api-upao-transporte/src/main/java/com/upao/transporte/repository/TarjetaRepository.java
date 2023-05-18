package com.upao.transporte.repository;

import com.upao.transporte.entity.Tarjeta;
import com.upao.transporte.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {
    Optional<Tarjeta> findById(Long id);
}
