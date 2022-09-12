package com.example.Tingeso.controllers;

import com.example.Tingeso.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Tingeso.entities.EmpleadoEntity;

import java.util.ArrayList;
@Controller
@RequestMapping
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @GetMapping("/lista")
    public String listar(Model model){
        ArrayList<EmpleadoEntity>empleados=empleadoService.obtenerEmpleados();
        model.addAttribute("empleados",empleados);
        return "index";
    }
    @GetMapping("/main")
    public String mainView(){
        return "main";
    }
}
