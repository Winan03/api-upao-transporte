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
    public ResponseEntity<Tarjeta> addTarjeta(@RequestBody Tarjeta tarjeta){
        return new ResponseEntity<Tarjeta>(tarjetaService.createTarjeta(tarjeta), HttpStatus.CREATED);
    }

}
