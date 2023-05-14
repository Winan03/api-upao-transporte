package com.upao.transporte.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "celular", nullable = false)
    private String celular;

    public boolean validarNombre() {
        // Validar que el nombre solo contenga letras y espacios
        return nombre.matches("[a-zA-Z\\s]+");
    }

    public boolean validarCorreo() {
        // Validar que el correo tenga el formato letras@letras.com
        return correo.matches("[a-zA-Z]+@[a-zA-Z]+\\.com");
    }

    public boolean validarContrasena() {
        // Validar que la contraseña tenga al menos 5 caracteres y al menos 1 número
        return contrasena.length() >= 5 && contrasena.matches(".*\\d.*");
    }

    public boolean validarCelular() {
        // Validar que el celular contenga solo números y tenga 9 dígitos
        return celular.matches("\\d{9}");
    }
}

