package com.example.Tingeso.repositories;

import com.example.Tingeso.entities.SubirDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirDataRepository extends JpaRepository <SubirDataEntity, Integer>{
}
