package com.upao.transporte.repository;

import com.upao.transporte.entity.Usuario;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombreAndCorreo(String nombre, String correoElectronico);
}
