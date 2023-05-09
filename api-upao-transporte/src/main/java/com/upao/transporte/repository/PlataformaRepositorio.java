package com.upao.transporte.repository;

import com.upao.transporte.entity.Usuario;
import org.springframework.data.jpa.repository.*;

    public interface PlataformaRepositorio extends JpaRepository<Usuario,Long> {

    }

