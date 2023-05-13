package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface PlataformaService {
    Usuario createUsuario(Usuario usuario);
    boolean loginUsuario(String nombre,String contrasena);
    Usuario modificarUsuario(Usuario usuario);
    Optional<Usuario> findById(Long id);
    void EliminarUsuario(Long id);
    RutaDeTransporte createRutaDeTransporte(RutaDeTransporte rutaDeTransporte);
    List<RutaDeTransporte> obtenerRutasDeTransporte();


}
