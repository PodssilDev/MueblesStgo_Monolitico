package com.example.Tingeso.services;

import com.example.Tingeso.entities.AutorizacionEntity;
import com.example.Tingeso.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    public void guardarAutorizacion(AutorizacionEntity autorizacion){
        this.autorizacionRepository.save(autorizacion);
    }

    public AutorizacionEntity buscarAutorizacion(String rut, String fecha){
        return this.autorizacionRepository.buscarAutorizacion(rut, fecha);
    }

    public void eliminarAutorizaciones(){
        this.autorizacionRepository.deleteAll();
    }
}
