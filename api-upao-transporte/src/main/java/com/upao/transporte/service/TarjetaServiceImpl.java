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

        if(String.valueOf(tarjeta.getNum()).length() != 16 || String.valueOf(tarjeta.getCvv()).length() != 3  ){
            throw new IllegalArgumentException("Usuario "+tarjeta.getUsuario().getNombre()+" intento agregar una tarjeta de" +
                    "credito pero hay un error al ingresar los datos , puede realizarlo de nuevo ");
        }
        return tarjetaRepository.save(tarjeta);
    }
}
