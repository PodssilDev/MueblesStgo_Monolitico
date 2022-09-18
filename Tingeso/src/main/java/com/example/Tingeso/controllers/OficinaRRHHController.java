package com.example.Tingeso.controllers;

import com.example.Tingeso.entities.OficinaRRHHEntity;
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
public class OficinaRRHHController {

    @Autowired
    private OficinaRRHHService reportePlanilla;

    @GetMapping("/reportePlanilla")
    public String listarReporte(Model model) throws ParseException {
        reportePlanilla.reportePlanilla();
        ArrayList<OficinaRRHHEntity> reporte = reportePlanilla.obtenerData();
        model.addAttribute("reporte", reporte);
        return "reportePlanilla";
    }

}
