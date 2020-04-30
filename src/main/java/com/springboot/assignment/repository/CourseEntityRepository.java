package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.entity.CourseEntity;

public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long>{

}
