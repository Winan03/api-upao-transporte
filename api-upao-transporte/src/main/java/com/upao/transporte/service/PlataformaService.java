package com.upao.transporte.service;

import com.upao.transporte.entity.Usuario;
public interface PlataformaService {
    Usuario createUsuario(Usuario usuario);
    boolean loginUsuario(String nombre,String contrasena);
}
