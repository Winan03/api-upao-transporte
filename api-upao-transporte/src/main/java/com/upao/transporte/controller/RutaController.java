package com.upao.transporte.controller;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rutas")
public class RutaController {
    @Autowired
    private final PlataformaService plataformaService;

    public RutaController(PlataformaService plataformaService){
        this.plataformaService=plataformaService;
    }
    @PostMapping
    public ResponseEntity<String> AddRuta(@RequestBody RutaDeTransporte rutaDeTransporte) {
        RutaDeTransporte rutaNueva = plataformaService.createRutaDeTransporte(rutaDeTransporte);

        if (rutaNueva != null) {
            String msj = "Nueva ruta " + rutaNueva.getOrigen()+" a "+ rutaNueva.getDestino()+" creada exitosamente";
            return ResponseEntity.status(HttpStatus.CREATED).body(msj);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear una nueva ruta");
        }
    }
}