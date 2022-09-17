package com.example.Tingeso.controllers;

import com.example.Tingeso.entities.EmpleadoEntity;
import com.example.Tingeso.services.EmpleadoService;
import com.example.Tingeso.services.OficinaRRHHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.ArrayList;

@Controller
@RequestMapping
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private OficinaRRHHService oficinaRRHH;
    @GetMapping("/lista")
    public String listar(Model model) throws ParseException {
        ArrayList<EmpleadoEntity>empleados=empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        System.out.println(oficinaRRHH.comprobarInasistencias("20.457.671-9","2022/06/08"));
        return "index";
    }
    @GetMapping("/main")
    public String mainView(){
        return "main";
    }
}
