package com.upao.transporte.controller;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.repository.RutaRepositorio;
import com.upao.transporte.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rutas")
public class RutaController {
    @Autowired
    private final PlataformaService plataformaService;

    public RutaController(PlataformaService plataformaService){this.plataformaService=plataformaService;
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
    //Eliminar rutas
    public void eliminarRuta(@PathVariable Long id){
        plataformaService.deleteById(id);
    }

    //Buscar Rutas por origen y destino


}