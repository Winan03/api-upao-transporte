package com.upao.transporte.service;

import com.upao.transporte.entity.Tarjeta;
import com.upao.transporte.repository.TarjetaRepository;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl implements TarjetaService{

    private final TarjetaRepository tarjetaRepository;
    //Inyeccion de dependencias
    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository){
        this.tarjetaRepository= tarjetaRepository;
    }

    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
}
