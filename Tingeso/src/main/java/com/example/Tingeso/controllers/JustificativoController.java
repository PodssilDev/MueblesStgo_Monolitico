package com.example.Tingeso.controllers;

import com.example.Tingeso.entities.JustificativoEntity;
import com.example.Tingeso.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;

    @GetMapping("/nuevoJustificativo")
    public String nuevoJustificativo(Model model){
        JustificativoEntity justificativo = new JustificativoEntity();
        model.addAttribute("justificativo", justificativo);
        return "subirJustificativo";
    }
    @PostMapping("/guardarJustificativo")
    public String guardarJustificativo(@ModelAttribute("justificativo") JustificativoEntity justificativo) {
        justificativoService.guardarJustificativo(justificativo);
        return "redirect:/nuevoJustificativo";
    }

}
