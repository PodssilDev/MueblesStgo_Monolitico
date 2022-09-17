package com.example.Tingeso.services;

import com.example.Tingeso.entities.OficinaRRHHEntity;
import com.example.Tingeso.repositories.OficinaRRHHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class OficinaRRHHService {

    @Autowired
    OficinaRRHHRepository oficinaRepository;

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    private SubirDataService subirData;
    @Autowired
    private JustificativoService justificativo;
    public double comprobarInasistencias(String rut, String fecha) throws ParseException {
        Calendar calendario = prepararCalendario(fecha);
        DateFormat dayFormat = new SimpleDateFormat("EEE");
        int lastDay = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        int sueldo_mensual = obtenerSueldo(empleadoService.obtenerCategoria(rut));
        double descuentos = 0.0;
        for(int day = 1; day<= lastDay; day++) {
            calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), day);
            Date day_name = calendario.getTime();
            String str_day_name = dayFormat.format(day_name);
            if (str_day_name.equals("sáb") || str_day_name.equals("dom")) {
                continue;
            }
            String formatted = date1.format(calendario.getTime());
            if (subirData.obtenerEspecifico(rut, formatted) == null){
                if (justificativo.buscarJustificativo(rut, formatted) != null) {
                    continue;
                } else {
                    descuentos = descuentos + (sueldo_mensual * 0.15);
                }
            }
            else{
                String hora_string = subirData.obtenerEspecifico(rut, formatted).getHora();
                SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
                Date hora = dt.parse(hora_string);
                if((hora.after(dt.parse("08:10"))) && (hora.before(dt.parse("08:25")))){
                    descuentos = descuentos + (sueldo_mensual* 0.01);
                } else if((hora.after(dt.parse("08:25"))) && (hora.before(dt.parse("08:45")))){
                    descuentos = descuentos + (sueldo_mensual * 0.03);
                } else if((hora.after(dt.parse("08:45"))) && (hora.before(dt.parse("09:10")))){
                    descuentos = descuentos + (sueldo_mensual * 0.06);
                } else if(hora.after(dt.parse("09:10"))){
                    if(justificativo.buscarJustificativo(rut, formatted) != null){
                        continue;
                    }
                    else{
                        descuentos = descuentos + (sueldo_mensual * 0.15);
                    }
                }
            }
        }
                // Aqui se deberia comprobar si asistio a la hora
        return descuentos;
    }
    public void insertarDatos(String rut){
        this.oficinaRepository.insertarDatos(rut);
    }

    public OficinaRRHHEntity insertarData(String rut){
        OficinaRRHHEntity data = new OficinaRRHHEntity();
        data.setRut(rut);
        return this.oficinaRepository.save(data);
    }

    public Integer obtenerSueldo(String categoria){
        if(categoria.equals("A")){
            return 1700000;
        } else if (categoria.equals("B")) {
            return 1200000;
        }
        else{
            return 800000;
        }
    }

    public double comprobarHoras(String hora_string, Integer sueldo_mensual, double descuentos) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
        Date hora = dt.parse(hora_string);
        if((hora.after(dt.parse("08:10"))) && (hora.before(dt.parse("08:25")))){
            descuentos = descuentos + (sueldo_mensual* 0.01);
        } else if((hora.after(dt.parse("08:25"))) && (hora.before(dt.parse("08:45")))){
            descuentos = descuentos + (sueldo_mensual * 0.03);
        } else if((hora.after(dt.parse("08:45"))) && (hora.before(dt.parse("09:10")))) {
            descuentos = descuentos + (sueldo_mensual * 0.06);
        }
        return descuentos;
    }
        /*
    private Date formatHora(String hora_string) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
        Date hora = dt.parse(hora_string);
        return hora;
    }
    */
    private Calendar prepararCalendario(String fecha) throws ParseException {
        Calendar calendario = Calendar.getInstance();
        DateFormat date1=new SimpleDateFormat("yyyy/MM/dd");
        Date real_fecha = date1.parse(fecha);
        calendario.setTime(real_fecha);
        return calendario;
    }

    private Boolean comprobarFinesSemana(Calendar calendario){
        Date day_name = calendario.getTime();
        String str_day_name = dayFormat.format(day_name);
        if (str_day_name.equals("sáb") || str_day_name.equals("dom")) {
            return true;
        }
        
    }
}
