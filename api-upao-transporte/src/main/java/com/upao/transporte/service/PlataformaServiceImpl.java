package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.*;
import com.upao.transporte.entity.Usuario;
import com.upao.transporte.repository.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RutaRepositorio transporteRepositorio;
    @Autowired
    private RutaRepositorio rutaRepositorio;

    @Override
    public Usuario createUsuario(Usuario usuario) {

        if (!usuario.validarNombre()) {
            throw new IllegalArgumentException("El nombre ingresado es inválido.");
        }

        if (!usuario.validarCorreo()) {
            throw new IllegalArgumentException("El correo ingresado es inválido.");
        }

        if (!usuario.validarContrasena()) {
            throw new IllegalArgumentException("La contraseña ingresada es inválida.");
        }

        if (!usuario.validarCelular()) {
            throw new IllegalArgumentException("El número de celular ingresado es inválido.");
        }

        return usuarioRepositorio.save(usuario);
    }

    public boolean loginUsuario(String nombre, String contrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByNombre(nombre);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getContrasena().equals(contrasena);
        }

        return false;
    }
    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepositorio.findByNombre(nombre);
    }
    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        if (!usuario.validarNombre()) {
            throw new IllegalArgumentException("El nombre ingresado es inválido.");
        }

        if (!usuario.validarCorreo()) {
            throw new IllegalArgumentException("El correo ingresado es inválido.");
        }

        if (!usuario.validarContrasena()) {
            throw new IllegalArgumentException("La contraseña ingresada es inválida.");
        }

        if (!usuario.validarCelular()) {
            throw new IllegalArgumentException("El número de celular ingresado es inválido.");
        }
        return usuarioRepositorio.save(usuario);
    }
    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepositorio.findById(id);
    }
    @Override
    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRepositorio.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    public boolean existeUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        return usuarioOptional.isPresent();
    }
    public RutaDeTransporte createRutaDeTransporte(RutaDeTransporte rutaDeTransporte) {
        if(rutaDeTransporte.getHorarioSalida().isBefore(LocalDateTime.of(rutaDeTransporte.getHorarioSalida().toLocalDate(),LocalTime.of(6,0)))
                || rutaDeTransporte.getHorarioLlegada().isAfter(LocalDateTime.of(rutaDeTransporte.getHorarioLlegada().toLocalDate(),LocalTime.of(21,0))) ||
                rutaDeTransporte.getHorarioLlegada().isBefore(rutaDeTransporte.getHorarioSalida()) || rutaDeTransporte.getPrecio().compareTo(BigDecimal.ZERO) < 0  ||
                rutaDeTransporte.getCantPasajeros() < 1){
            throw new IllegalArgumentException("Datos de la ruta invalidos");
        }
        return transporteRepositorio.save(rutaDeTransporte);

    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public Usuario consultarUsuarioPorId (Long id) {

        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        return usuarioOptional.orElse(null);
    }

    @Override
    public void eliminarRutaDeTransporte2(Long id) {
        rutaRepositorio.deleteById(String.valueOf(id));
    }
}

