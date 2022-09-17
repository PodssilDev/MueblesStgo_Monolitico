package com.example.Tingeso.services;

import com.example.Tingeso.entities.SubirDataEntity;
import com.example.Tingeso.repositories.SubirDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubirDataService {
    @Autowired
    SubirDataRepository dataRepository;
    private String carpeta = "src/main/resources/cargas//";
    private final Logger logg = LoggerFactory.getLogger(SubirDataService.class);

    public ArrayList<SubirDataEntity> obtenerData(){
        return (ArrayList<SubirDataEntity>) dataRepository.findAll();
    }
    public String guardar(MultipartFile file){
        if(!file.isEmpty()) {
            try{
                byte [] bytes = file.getBytes();
                Path path  = Paths.get(carpeta + file.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("Archivo guardado");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return "Archivo guardado con exito!";
    }

    public String leerTxt(String direccion){
        String texto = "";
        dataRepository.deleteAll();
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                temp = temp + "\n" + bfRead;
            }
            texto = temp;
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }
        return texto;
    }

    public SubirDataEntity guardarData(SubirDataEntity data){
        return dataRepository.save(data);
    }

    public void guardarDataDB(String fecha, String hora, String rut){
         SubirDataEntity newData = new SubirDataEntity();
         newData.setFecha(fecha);
         newData.setRut(rut);
         newData.setHora(hora);
         guardarData(newData);
    }

    public SubirDataEntity obtenerEspecifico(String rut, String fecha){
        return dataRepository.buscarData(rut, fecha);
    }

    public List<String> obtenerRuts(){
        return dataRepository.findDistinctRut();
    }

}
