package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.entity.WaterTankEntity;

public interface WaterTankEntityRepository extends JpaRepository<WaterTankEntity, Long>{

}
