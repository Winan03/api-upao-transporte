package com.upao.transporte.controller;

import com.upao.transporte.entity.Tarjeta;
import com.upao.transporte.repository.TarjetaRepository;
import com.upao.transporte.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {
    private final TarjetaService tarjetaService;


    public TarjetaController(TarjetaService tarjetaService){
        this.tarjetaService= tarjetaService;
    }

    @PostMapping
    public ResponseEntity<String> addTarjeta(@RequestBody Tarjeta tarjeta){
        Tarjeta tarjetaCreada = tarjetaService.createTarjeta(tarjeta);
        if (tarjetaCreada != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha agregado una tarjeta exitosamente");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la tarjeta ");
        }

    }

}
