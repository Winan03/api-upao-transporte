package com.upao.transporte.controller;

import com.upao.transporte.entity.Tarjeta;
import com.upao.transporte.entity.Usuario;
import com.upao.transporte.repository.TarjetaRepository;
import com.upao.transporte.service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping("modificarTarjeta/{id}")
    public ResponseEntity<String> updateTarjeta(@PathVariable Long id,@RequestBody Tarjeta tarjeta){
        Optional<Tarjeta> tarjetaExistenteOpccional = tarjetaService.findById(id);
        if(tarjetaExistenteOpccional.isPresent()){
            Tarjeta tarjetaExistente = tarjetaExistenteOpccional.get();
            tarjetaExistente.setNum(tarjeta.getNum());
            tarjetaExistente.setFechaVenc(tarjeta.getFechaVenc());
            tarjetaExistente.setCvv(tarjeta.getCvv());

            tarjetaService.modificarTarjeta(tarjetaExistente);

            return ResponseEntity.ok("Usuario "+tarjetaExistente.getUsuario().getNombre()+" su tarjeta se ha actualizado correctamente ");

        }

        return ResponseEntity.ok("Tarjeta actualizado correctamente: " + id);
    }

    @DeleteMapping("eliminarTarjeta/{id}")
    public ResponseEntity<String> eliminarTarjeta(@PathVariable Long id) {
        tarjetaService.eliminarTarjeta(id);
        return ResponseEntity.ok("Tarjeta eliminado correctamente");
    }

}
