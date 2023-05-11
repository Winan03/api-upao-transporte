package com.upao.transporte.controller;

import com.upao.transporte.entity.Usuario;
import com.upao.transporte.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/users")
public class PlataformaController {
    @Autowired
    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService){
        this.plataformaService=plataformaService;
    }

    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){

        Usuario usuarioCreado = plataformaService.createUsuario(usuario);

        if (usuarioCreado != null) {
            String mensaje = "Usuario registrado correctamente: " + usuarioCreado.getNombre();
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario");
        }

    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Usuario request) {
        String username = request.getNombre();
        String password = request.getContrasena();

        boolean validarLogin = plataformaService.loginUsuario(username, password);

        if (validarLogin) {
            return ResponseEntity.ok("Acceso exitoso, Bienvenido a la plataforma usuario "+username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El nombre de usuario o la contraseña son incorrectos");
        }

    }

}

