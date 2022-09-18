package com.example.Tingeso.controllers;

import com.example.Tingeso.entities.AutorizacionEntity;
import com.example.Tingeso.services.AutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping
public class AutorizacionController {

    @Autowired
    private AutorizacionService autorizacionService;

    @GetMapping("/nuevaAutorizacion")
    public String nuevaAutorizacion(Model model) throws ParseException {
        AutorizacionEntity autorizacion = new AutorizacionEntity();
        model.addAttribute("autorizacion", autorizacion);
        return "subirAutorizacion";
    }

    @PostMapping("/guardarAutorizacion")
    public String guardarAutorizacion(@ModelAttribute("autorizacion") AutorizacionEntity autorizacion){
        autorizacionService.guardarAutorizacion(autorizacion);
        return "redirect:/nuevaAutorizacion";
    }


}
