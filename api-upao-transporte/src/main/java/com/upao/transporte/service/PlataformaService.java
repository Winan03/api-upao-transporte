package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.entity.Usuario;

import java.util.Optional;

public interface PlataformaService {
    Usuario createUsuario(Usuario usuario);
    boolean loginUsuario(String nombre,String contrasena);
    Usuario modificarUsuario(Usuario usuario);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findById(Long id);
    boolean eliminarUsuario(Long id);
    boolean existeUsuario(Long id);
    Usuario consultarUsuarioPorId (Long id);
    RutaDeTransporte createRutaDeTransporte(RutaDeTransporte rutaDeTransporte);
    void deleteById(Long id);
    void eliminarRutaDeTransporte2(Long id);
}
