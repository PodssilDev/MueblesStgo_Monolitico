package com.example.tingeso;

import com.example.tingeso.services.OficinaRRHHService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class OficinaRRHHTest {

    @Autowired
    OficinaRRHHService oficinaService;


    @Test
    void testExtraCategoria(){
        double sueldo = oficinaService.extraCategoria("B", 10);
        assertEquals(200000.0,sueldo,0.0);

        sueldo = oficinaService.extraCategoria("C", 5);
        assertEquals(50000.0, sueldo, 0.0);

        sueldo = oficinaService.extraCategoria("A", 99);
        assertEquals(2475000.0,sueldo,0.0);

        sueldo = oficinaService.extraCategoria("X", 3);
        assertEquals(0.0, sueldo, 0.0);
    }

    @Test
    void testCalcularBonificacionDedicacion() {
        double bonificacion = oficinaService.calcularBonificacionDedicacion(1700000, 8);
        assertEquals(85000.0, bonificacion, 0.0);

        bonificacion = oficinaService.calcularBonificacionDedicacion(1200000, 12);
        assertEquals(96000.0, bonificacion, 0.0);

        bonificacion = oficinaService.calcularBonificacionDedicacion(800000, 18);
        assertEquals(88000.0, bonificacion, 0.0);

        bonificacion = oficinaService.calcularBonificacionDedicacion(1700000, 21);
        assertEquals(238000.0, bonificacion, 0.1);

        bonificacion = oficinaService.calcularBonificacionDedicacion(1200000, 28);
        assertEquals(204000.0, bonificacion, 0.1);

        bonificacion = oficinaService.calcularBonificacionDedicacion(-10000, 2);
        assertEquals(0.0, bonificacion, 0.0);

        bonificacion = oficinaService.calcularBonificacionDedicacion(800000, 0);
        assertEquals(0.0, bonificacion, 0.0);
    }
}