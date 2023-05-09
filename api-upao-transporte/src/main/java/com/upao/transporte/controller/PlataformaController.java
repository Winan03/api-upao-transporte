
package com.upao.transporte.controller;

import com.upao.transporte.entity.Usuario;
import com.upao.transporte.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/v1/users")
    public class PlataformaController {
        @Autowired
        private final PlataformaService plataformaService;

        public PlataformaController(PlataformaService plataformaService){
            this.plataformaService=plataformaService;
        }

        @PostMapping
        public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
            return new ResponseEntity<Usuario>(plataformaService.createUsuario(usuario),HttpStatus.CREATED);
        }
    }