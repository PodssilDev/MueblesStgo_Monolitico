package com.example.Tingeso.repositories;

import com.example.Tingeso.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String>{
    @Query("select e from EmpleadoEntity e where e.nombres = :nombres")
    EmpleadoEntity findByNameCustomQuery(@Param("nombres") String nombres);
}
