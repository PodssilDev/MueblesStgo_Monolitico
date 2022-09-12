package com.example.Tingeso.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubirDataEntity {
    @Id
    @NotNull
    @GeneratedValue
    private Integer ID;
    private String fecha;
    private String hora;
    private String rut;
}
