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
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        String username = usuario.getNombre();
        String password = usuario.getContrasena();

        if (!usuario.validarNombre()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ingreso mal su nombre, solo debe contener letras y espacios.");
        }

        if (!usuario.validarContrasena()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ingreso mal su contraseña, necesita tener al menos 5 caracteres y al menos 1 número.");
        }

        Optional<Usuario> usuarioOptional = plataformaService.findByNombre(username);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El nombre de usuario no existe en la base de datos. Por favor, ingrese un nombre válido.");
        }

        Usuario usuarioEncontrado = usuarioOptional.get();
        if (!usuarioEncontrado.getContrasena().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña es incorrecta. Por favor, ingrese una contraseña válida.");
        }

        return ResponseEntity.ok("Acceso exitoso, Bienvenido a la plataforma usuario " + username);
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

    @GetMapping("consulta/{id}")
    public ResponseEntity<Usuario> consultarInformacionUsuario(@PathVariable Long id) {
        Usuario user = plataformaService.consultarUsuarioPorId(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


