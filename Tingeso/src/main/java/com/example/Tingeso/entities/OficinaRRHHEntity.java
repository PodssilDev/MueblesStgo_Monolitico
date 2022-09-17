package com.example.Tingeso.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "planilla_sueldos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OficinaRRHHEntity {
    @Id
    @NotNull
    private String rut;
    private String nombre_empleado;
    private String categoria;
    private String total_servicio;
    private String sueldo_mensual;
}
