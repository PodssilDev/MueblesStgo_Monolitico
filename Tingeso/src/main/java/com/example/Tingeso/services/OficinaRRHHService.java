package com.example.Tingeso.services;

import com.example.Tingeso.entities.EmpleadoEntity;
import com.example.Tingeso.entities.OficinaRRHHEntity;
import com.example.Tingeso.repositories.OficinaRRHHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OficinaRRHHService {

    @Autowired
    private OficinaRRHHRepository oficinaRepository;

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private SubirDataService subirData;
    @Autowired
    private JustificativoService justificativo;

    @Autowired
    private AutorizacionService autorizacion;

    public void reportePlanilla() throws ParseException {
        oficinaRepository.deleteAll();
        List<String>  listaRuts = subirData.obtenerRuts();
        for(int i = 0; i < listaRuts.size(); i++){
            calculoPlanilla(listaRuts.get(i));
        }
    }

    public void calculoPlanilla(String rut) throws ParseException {
        EmpleadoEntity empleadoActual = empleadoService.findByRut(rut);
        OficinaRRHHEntity empleado_reporte = new OficinaRRHHEntity();
        empleado_reporte.setRut(empleadoActual.getRut());
        empleado_reporte.setNombre_empleado(empleadoActual.getApellidos() + " " + empleadoActual.getNombres());
        empleado_reporte.setCategoria(empleadoActual.getCategoria());
        empleado_reporte.setDedicacion(calcularDedicacion(empleadoActual.getFecha_ingreso(), subirData.obtenerFechaRut(empleadoActual.getRut())));
        empleado_reporte.setSueldo_mensual(obtenerSueldo(empleadoActual.getCategoria()));
        empleado_reporte.setBonificacion_dedicacion(calcularBonificacionDedicacion(empleado_reporte.getSueldo_mensual(), empleado_reporte.getDedicacion()));
        empleado_reporte.setHoras_extras(calcularMontoExtra(empleadoActual.getRut(), subirData.obtenerFechaRut(empleadoActual.getRut())));
        empleado_reporte.setDescuentos(comprobarDescuentos(empleadoActual.getRut(), subirData.obtenerFechaRut(empleadoActual.getRut())));
        empleado_reporte.setSueldo_bruto(calcularSueldoBruto(empleado_reporte));
        empleado_reporte.setPrevisional(empleado_reporte.getSueldo_bruto() * 0.1);
        empleado_reporte.setSalud(empleado_reporte.getSueldo_bruto() * 0.08);
        empleado_reporte.setSueldo_final(empleado_reporte.getSueldo_bruto() - empleado_reporte.getPrevisional() - empleado_reporte.getSalud());
        oficinaRepository.save(empleado_reporte);
    }

    public double calcularSueldoBruto(OficinaRRHHEntity empleado){
        double sueldoBruto = (empleado.getSueldo_mensual() + empleado.getBonificacion_dedicacion() + empleado.getHoras_extras() - empleado.getDescuentos());
        if(sueldoBruto < 0.0){
            return 0.0;
        }
        else{
            return sueldoBruto;
        }
    }
    public double calcularMontoExtra(String rut, String fecha_inicial) throws ParseException {
        Calendar calendario = prepararCalendario(fecha_inicial);
        int lastDay = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        double montoExtra = 0.0;
        for(int day = 1; day<= lastDay; day++) {
            calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), day);
            if (!(comprobarFinesSemana(calendario))) {
                String fecha_real = formatDate(calendario);
                if(subirData.obtenerEspecifico2(rut, fecha_real) != null){
                    if(autorizacion.buscarAutorizacion(rut,fecha_real) != null){
                        String hora = subirData.obtenerEspecifico2(rut,fecha_real).getHora();
                        montoExtra = montoExtra + extraCategoria(empleadoService.findByRut(rut).getCategoria(), contarHoras(hora));
                    }
                }
            }

        }
        return montoExtra;
    }

    public double extraCategoria(String categoria, Integer contador){
        if(categoria.equals("A")){
            return (25000 * contador);
        } else if (categoria.equals("B")) {
            return (20000 * contador);
        } else{
            return (10000 * contador);
        }
    }

    public Integer contarHoras(String hora_string) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
        Date hora = dt.parse(hora_string);
        int contador = 0;
        if(hora.after(dt.parse("18:00"))){
            contador = (int)((hora.getTime() - dt.parse("18:00").getTime()) / (60 * 60 * 1000));
        }
        return contador;
    }

    public Integer calcularDedicacion(String fecha_inicio, String fecha_temporal) throws ParseException {
        Calendar calendario = prepararCalendario(fecha_inicio);
        Calendar calendario2 = prepararCalendario(fecha_temporal);
        calendario2.set(calendario2.get(Calendar.YEAR), calendario2.get(Calendar.MONTH), calendario2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date1 = calendario.getTime();
        Date date2 =calendario2.getTime();
        return Math.toIntExact(((date2.getTime() - date1.getTime()) / 86400000 / 365));
    }

    public double calcularBonificacionDedicacion(Integer sueldo_mensual, Integer dedicacion){
        if((dedicacion >= 5) && (dedicacion < 10)){
            return (sueldo_mensual * 0.05);
        } else if((dedicacion >= 10) && (dedicacion < 15)){
            return (sueldo_mensual * 0.08);
        } else if((dedicacion >= 15) && (dedicacion < 20)){
            return (sueldo_mensual * 0.11);
        } else if((dedicacion >= 20) && (dedicacion < 25)){
            return (sueldo_mensual * 0.14);
        } else if(dedicacion >= 25){
            return (sueldo_mensual * 0.17);
        } else{
         return 0.0;
        }
    }

    public double comprobarDescuentos(String rut, String fecha) throws ParseException {
        Calendar calendario = prepararCalendario(fecha);
        int lastDay = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        double descuentos = 0.0;
        for(int day = 1; day<= lastDay; day++) {
            calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), day);
            if (!(comprobarFinesSemana(calendario))){
                String fecha_real = formatDate(calendario);
                if (subirData.obtenerEspecifico(rut, fecha_real) == null){ // NO hay registro del rut y fecha en DATA TXT
                    descuentos = comprobarJustificativo(rut, fecha_real, descuentos);
                }
                else{
                    String hora_string = subirData.obtenerEspecifico(rut, fecha_real).getHora();
                    descuentos = comprobarHoras(hora_string, rut, descuentos);
                    if(comprobarAtrasado(hora_string)){
                        descuentos = comprobarJustificativo(rut, fecha_real, descuentos);
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

    public double comprobarHoras(String hora_string,String rut, double descuentos) throws ParseException {
        int sueldo_mensual = obtenerSueldo(empleadoService.obtenerCategoria(rut));
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
    private Boolean comprobarAtrasado(String hora_string) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
        Date hora = dt.parse(hora_string);
        if(hora.after(dt.parse("09:10"))){
            return true;
        }
        else{
            return false;
        }
    }

    private Calendar prepararCalendario(String fecha) throws ParseException {
        Calendar calendario = Calendar.getInstance();
        DateFormat date1=new SimpleDateFormat("yyyy/MM/dd");
        Date real_fecha = date1.parse(fecha);
        calendario.setTime(real_fecha);
        return calendario;
    }

    private Boolean comprobarFinesSemana(Calendar calendario){
        DateFormat dayFormat = new SimpleDateFormat("EEE");
        Date day_name = calendario.getTime();
        String str_day_name = dayFormat.format(day_name);
        if (str_day_name.equals("sáb") || str_day_name.equals("dom")) {
            return true;
        }
        else{
            return false;
        }
    }

    private String formatDate(Calendar calendario){
        DateFormat date1=new SimpleDateFormat("yyyy/MM/dd");
        String fecha = date1.format(calendario.getTime());
        return fecha;
    }

    private double comprobarJustificativo(String rut, String fecha, double descuentos){
        int sueldo_mensual = obtenerSueldo(empleadoService.obtenerCategoria(rut));
        if (justificativo.buscarJustificativo(rut, fecha) != null) {
            return descuentos;
        } else{
            descuentos = descuentos + (sueldo_mensual * 0.15);
            return descuentos;
        }

    }


    public ArrayList<OficinaRRHHEntity> obtenerData(){
        return (ArrayList<OficinaRRHHEntity>) oficinaRepository.findAll();
    }
}
