package com.example.Tingeso.services;

import com.example.Tingeso.entities.EmpleadoEntity;
import com.example.Tingeso.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }

    public EmpleadoEntity obtenerEspecifico(){
        return empleadoRepository.findByNameCustomQuery("asd");
    }

    public String obtenerCategoria(String rut){
        return empleadoRepository.findCategory(rut);
    }

}
