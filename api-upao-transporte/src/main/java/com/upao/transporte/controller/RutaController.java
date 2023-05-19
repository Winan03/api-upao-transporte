package com.upao.transporte.controller;

import com.upao.transporte.entity.RutaDeTransporte;
import com.upao.transporte.service.PlataformaService;
import com.upao.transporte.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rutas")
public class RutaController{
    @Autowired
    private final PlataformaService plataformaService;
    private final RutaService rutaService;


    public RutaController(PlataformaService plataformaService, RutaService rutaService){this.plataformaService=plataformaService;
        this.rutaService = rutaService;
    }
    @PostMapping
    public ResponseEntity<String> AddRuta(@RequestBody RutaDeTransporte rutaDeTransporte) {
        try {
            RutaDeTransporte rutaNueva = plataformaService.createRutaDeTransporte(rutaDeTransporte);
            String msj = "Nueva ruta " + rutaNueva.getOrigen() + " a " + rutaNueva.getDestino() + " creada exitosamente";
            return ResponseEntity.status(HttpStatus.CREATED).body(msj);
        } catch (IllegalArgumentException e) {
            if (!rutaDeTransporte.validarOrigen()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El origen solo debe contener números, letras y espacios.");
            } else if (!rutaDeTransporte.validarDestino()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El destino solo debe contener números, letras y espacios.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la ruta.");
            }
        }
    }
    //Modificar rutas
    @PutMapping("modificarRutaDeTransporte/{id}")
    public ResponseEntity<String> updateRutaDeTransporte(@PathVariable Long id, @RequestBody RutaDeTransporte ruta) {


        try {
            Optional<RutaDeTransporte> rutaDeTransporteOpcional = rutaService.findById(id);

            if(rutaDeTransporteOpcional.isPresent()){
                RutaDeTransporte rutaExistente = rutaDeTransporteOpcional.get();
                rutaExistente.setOrigen(ruta.getOrigen());
                rutaExistente.setDestino(ruta.getDestino());
                rutaExistente.setHorarioSalida(ruta.getHorarioSalida());
                rutaExistente.setHorarioLlegada(ruta.getHorarioLlegada());
                rutaExistente.setPrecio(ruta.getPrecio());
                rutaExistente.setCantPasajeros(ruta.getCantPasajeros());


                rutaService.modificarRutaDeTransporte(rutaExistente);

                return ResponseEntity.ok("Ruta actualizada correctamente ");
            }

            return ResponseEntity.ok("Ruta actualizada correctamente: " + id);

        } catch (IllegalArgumentException e) {
            if (!ruta.validarOrigen()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El origen debe contener solo números, letras y espacios.");
            } else if (!ruta.validarDestino()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El destino debe contener solo números, letras y espacios");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la ruta Verifique bien si ha ingresado correctamente los datos.");
            }
        }
    }
    //Eliminar rutas
    @DeleteMapping("eliminarRutaDeTransporte/{id}")
    public ResponseEntity<String> eliminarRuta(@PathVariable Long id) {
        plataformaService.eliminarRutaDeTransporte2(id);
        return ResponseEntity.ok("Ruta eliminada correctamente");
    }

    //Buscar Rutas por origen y destino


}