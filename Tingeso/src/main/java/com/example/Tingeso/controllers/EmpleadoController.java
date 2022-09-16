package com.example.Tingeso.controllers;

import com.example.Tingeso.entities.EmpleadoEntity;
import com.example.Tingeso.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @GetMapping("/lista")
    public String listar(Model model) throws ParseException {
        ArrayList<EmpleadoEntity>empleados=empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        String sDate1="2022/2/1";
        DateFormat date1=new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = date1.parse(sDate1);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        String fecha_final = (date1.format(calendario.getTime()));
        System.out.println(fecha_final);
        int lastday = calendario.getActualMaximum(calendario.DAY_OF_MONTH);
        System.out.println(lastday);
        return "index";
    }
    @GetMapping("/main")
    public String mainView(){
        return "main";
    }
}
