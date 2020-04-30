package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.entity.StudentEntity;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long>{

}
