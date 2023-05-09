
package com.upao.transporte.service;

import org.springframework.stereotype.*;
import com.upao.transporte.entity.Usuario;
import com.upao.transporte.repository.PlataformaRepositorio;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    private final PlataformaRepositorio plataformaRepositorio;

    public PlataformaServiceImpl(PlataformaRepositorio plataformaRepositorio) {
        this.plataformaRepositorio = plataformaRepositorio;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return plataformaRepositorio.save(usuario);
    }
}