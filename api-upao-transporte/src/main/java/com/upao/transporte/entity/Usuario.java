package com.upao.transporte.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre",nullable = false)
    private String nombre;
    @Column(name="correoElectronico",nullable = false)
    private String correoElectronico;
    @Column(name="contrasena",nullable = false)
    private String contrasena;
    @Column(name="celular",nullable = false)
    private String celular;

}

