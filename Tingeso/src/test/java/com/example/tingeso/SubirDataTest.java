package com.example.tingeso;

import com.example.tingeso.entities.SubirDataEntity;
import com.example.tingeso.services.SubirDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SubirDataTest {

    @Autowired
    SubirDataService subirData;

    @Test
    void testGuardarDataDB(){
        subirData.guardarDataDB("2022/10/06", "08:00", "20.537.567-8");
        SubirDataEntity newData = subirData.obtenerEspecifico("20.537.567-8", "2022/10/06");
        assertEquals("20.537.567-8", newData.getRut());
        subirData.eliminarData(subirData.obtenerData(newData.getRut()));
    }

    @Test
    void testEncontrarData() throws ParseException {
        subirData.insertarData("20.537.567-8", "2022/10/01");
        SubirDataEntity newData = subirData.obtenerEspecifico2("20.537.567-8", "2022/10/06");
        assertEquals("18:00", newData.getHora());
        subirData.eliminarData(subirData.obtenerData(newData.getRut()));
    }


}
