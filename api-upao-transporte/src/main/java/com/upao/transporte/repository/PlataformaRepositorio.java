package com.upao.transporte.repository;

import com.upao.transporte.entity.Usuario;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface PlataformaRepositorio extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByNombre(String nombre);
}
