package com.upao.transporte.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="rutaTransporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class RutaDeTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "origen")
    private String origen;
    @Column(name = "destino")
    private String destino;
    @Column(name = "horario_salida")
    private LocalDateTime horarioSalida;
    @Column(name = "horario_llegada")
    private LocalDateTime horarioLlegada;
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name="cant_Pasajeros")
    private int cantPasajeros;

    public boolean validarOrigen(){
        //Valida que el origen contenga solo números y letras
        return origen.matches("^[a-zA-Z0-9]+$");
    }
    public boolean validarDestino(){
        //Valida que el destino contenga solo números y letras
        return destino.matches("^[a-zA-Z0-9]+$");
    }
}

