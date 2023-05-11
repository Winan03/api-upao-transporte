package com.upao.transporte.service;

import org.springframework.stereotype.*;
import com.upao.transporte.entity.Usuario;
import com.upao.transporte.repository.PlataformaRepositorio;

import java.util.Optional;

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

    public boolean loginUsuario(String nombre, String contrasena) {

        Optional<Usuario> usuarioOptional = plataformaRepositorio.findByNombre(nombre);

        // Verifica si el usuario existe y si la contraseña es correcta
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getContrasena().equals(contrasena);
        }

        return false; // Usuario no encontrado
    }
}
