package com.upao.transporte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tarjetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="numero",nullable = false)
    private String num;
    @Column(name="fechaVencimiento",nullable = false)
    private String fechaVenc;
    @Column(name="cvv",nullable = false)
    private int cvv;

}
