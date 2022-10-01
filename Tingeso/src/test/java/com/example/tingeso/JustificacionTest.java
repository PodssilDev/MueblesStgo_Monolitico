package com.example.tingeso;

import com.example.tingeso.services.JustificativoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@SpringBootTest
public class JustificacionTest {

    @Autowired
    JustificativoService justificacion;
    /*
    @Test
    void testInsertarJustificativo(){
        Map<String,String> ourMap = new HashMap<String,String>();
        ourMap.put("20.537.567-8", "2022/06/03");
        //justificacion.guardarJustificativo();

    }
    */
}
