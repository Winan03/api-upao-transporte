package com.upao.transporte.service;

import com.upao.transporte.entity.RutaDeTransporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import com.upao.transporte.entity.Usuario;
import com.upao.transporte.repository.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RutaRepositorio transporteRepositorio;

    @Override
    public Usuario createUsuario(Usuario usuario) {
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

    public List<RutaDeTransporte> obtenerRutasDeTransporte() {
        return transporteRepositorio.findAll();
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {

        return usuarioRepositorio.save(usuario);
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
}

