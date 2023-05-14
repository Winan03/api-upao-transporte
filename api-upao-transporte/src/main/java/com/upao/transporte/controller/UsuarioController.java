package com.upao.transporte.controller;

import com.upao.transporte.entity.Usuario;
import com.upao.transporte.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {
    @Autowired
    private final PlataformaService plataformaService;

    public UsuarioController(PlataformaService plataformaService){
        this.plataformaService=plataformaService;
    }

    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){


        try {
            Usuario usuarioCreado = plataformaService.createUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario "+usuarioCreado.getNombre()+" registrado correctamente");
        } catch (IllegalArgumentException e) {

            if (!usuario.validarNombre()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre debe contener solo letras y espacios.");
            } else if (!usuario.validarCorreo()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo no tiene un formato válido.");
            } else if (!usuario.validarContrasena()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña debe tener al menos 5 caracteres y al menos 1 número.");
            } else if (!usuario.validarCelular()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número de celular debe contener solo números y tener 9 dígitos.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario. Verifique bien si ha ingresado correctamente los datos.");
                }
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

    @PutMapping("modificarUsuario/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {

        Optional<Usuario> usuarioExistenteOpccional = plataformaService.findById(id);

        if(usuarioExistenteOpccional.isPresent()){
            Usuario usuarioExistente = usuarioExistenteOpccional.get();
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setContrasena(usuario.getContrasena());
            usuarioExistente.setCelular(usuario.getCelular());

            plataformaService.modificarUsuario(usuarioExistente);

            return ResponseEntity.ok("Usuario "+usuarioExistente.getNombre()+" actualizado correctamente ");
        }

        return ResponseEntity.ok("Usuario actualizado correctamente: " + id);
    }

    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        plataformaService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    @GetMapping("consultarUsuario")
    public ResponseEntity<Usuario> consultUserXnameYcorreo( @RequestBody Usuario usuario) {

        String ussername = usuario.getNombre();
        String correo = usuario.getCorreo();
        Usuario user = plataformaService.consultarInformacionUsuario(ussername,correo);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


