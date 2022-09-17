package com.example.Tingeso.repositories;

import com.example.Tingeso.entities.SubirDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubirDataRepository extends JpaRepository <SubirDataEntity, Integer>{


    @Query(value = "select * from data as e where e.rut = :rut and e.fecha =:fecha limit 1",
            nativeQuery = true)
    SubirDataEntity buscarData(@Param("rut") String rut, @Param("fecha") String fecha);

    @Query(value = "select distinct rut from data", nativeQuery = true)
    List<String> findDistinctRut();

}
