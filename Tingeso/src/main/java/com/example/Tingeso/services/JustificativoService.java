package com.example.Tingeso.services;

import com.example.Tingeso.entities.JustificativoEntity;
import com.example.Tingeso.repositories.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificativoService {

    @Autowired
    private JustificativoRepository justificativoRepository;

    public void guardarJustificativo(JustificativoEntity justificativo){
        this.justificativoRepository.save(justificativo);
    }
}
