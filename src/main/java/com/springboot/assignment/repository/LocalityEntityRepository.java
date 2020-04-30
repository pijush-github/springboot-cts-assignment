package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.entity.LocalityEntity;

public interface LocalityEntityRepository extends JpaRepository<LocalityEntity, Long> {

}
