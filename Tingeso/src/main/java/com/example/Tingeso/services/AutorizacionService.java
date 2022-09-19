package com.example.Tingeso.services;

import com.example.Tingeso.entities.AutorizacionEntity;
import com.example.Tingeso.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    public void guardarAutorizacion(Map request){
        AutorizacionEntity autorizacion = new AutorizacionEntity();
        autorizacion.setRut(request.get("rut").toString());
        autorizacion.setFecha(request.get("fecha").toString());
        this.autorizacionRepository.save(autorizacion);
    }

    public AutorizacionEntity buscarAutorizacion(String rut, String fecha){
        return this.autorizacionRepository.buscarAutorizacion(rut, fecha);
    }

    public void eliminarAutorizaciones(){
        this.autorizacionRepository.deleteAll();
    }
}
