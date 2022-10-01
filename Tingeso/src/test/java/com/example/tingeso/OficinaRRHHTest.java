package com.example.tingeso;

import com.example.tingeso.entities.EmpleadoEntity;
import com.example.tingeso.entities.OficinaRRHHEntity;
import com.example.tingeso.repositories.EmpleadoRepository;
import com.example.tingeso.repositories.OficinaRRHHRepository;
import com.example.tingeso.repositories.SubirDataRepository;
import com.example.tingeso.services.OficinaRRHHService;
import com.example.tingeso.services.SubirDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class OficinaRRHHTest {

    @Autowired
    OficinaRRHHService oficinaService;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    SubirDataService data;


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
    /*
    @Test
    void testContarHoras() throws ParseException {
        String hora = "20:00";
        Integer contador = oficinaService.contarHoras(hora);
        assertEquals(2, contador, 0.0);
    }

    @Test
    void testMetodosCalculos() throws ParseException {
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRut("20.942.821-8");
        empleado.setCategoria("A");
        empleado.setNombres("JOHN PATRICIO");
        empleado.setApellidos("SERRANO CARRASCO");
        empleado.setFecha_ingreso("2010/06/12");
        empleadoRepository.save(empleado);
        data.insertarData(empleado.getRut(), "2022/06/01");
        OficinaRRHHEntity empleado_reporte = new OficinaRRHHEntity();
        empleado_reporte.setRut(empleado.getRut());
        empleado_reporte.setNombre_empleado(empleado.getApellidos() + " " + empleado.getNombres());
        empleado_reporte.setCategoria(empleado.getCategoria());
        empleado_reporte.setDedicacion(oficinaService.calcularDedicacion(empleado.getFecha_ingreso(), data.obtenerFechaRut(empleado.getRut())));
        empleado_reporte.setSueldo_mensual(oficinaService.obtenerSueldo(empleado.getCategoria()));
        empleado_reporte.setBonificacion_dedicacion(oficinaService.calcularBonificacionDedicacion(empleado_reporte.getSueldo_mensual(), empleado_reporte.getDedicacion()));
        empleado_reporte.setHoras_extras(oficinaService.calcularMontoExtra(empleado.getRut(), data.obtenerFechaRut(empleado.getRut())));
        empleado_reporte.setDescuentos(oficinaService.comprobarDescuentos(empleado.getRut(), data.obtenerFechaRut(empleado.getRut())));
        empleado_reporte.setSueldo_bruto(oficinaService.calcularSueldoBruto(empleado_reporte));
        empleado_reporte.setPrevisional(empleado_reporte.getSueldo_bruto() * 0.1);
        empleado_reporte.setSalud(empleado_reporte.getSueldo_bruto() * 0.08);
        empleado_reporte.setSueldo_final(empleado_reporte.getSueldo_bruto() - empleado_reporte.getPrevisional() - empleado_reporte.getSalud());
        assertEquals(1463700.0, empleado_reporte.getSueldo_final(), 0.0);
        data.eliminarData(data.obtenerData(empleado.getRut()));
        empleadoRepository.delete(empleado);
    }

    @Test
    void testCalculoFull() throws ParseException {
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setRut("20.942.821-8");
        empleado.setCategoria("B");
        empleado.setNombres("JOHN PATRICIO");
        empleado.setApellidos("SERRANO CARRASCO");
        empleado.setFecha_ingreso("2010/06/12");
        empleadoRepository.save(empleado);
        data.insertarData(empleado.getRut(), "2022/06/01");
        oficinaService.calculoPlanilla(empleado.getRut());
        double sueldo = oficinaService.encontrarRut(empleado.getRut()).getSueldo_final();
        assertEquals(1033200.0,  sueldo, 0.0);
        oficinaService.eliminarData(oficinaService.encontrarRut(empleado.getRut()));
        empleadoRepository.delete(empleado);
    }
    */
}