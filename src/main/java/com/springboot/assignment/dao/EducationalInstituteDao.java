package com.springboot.assignment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.entity.CourseEntity;
import com.springboot.assignment.entity.StudentEntity;
import com.springboot.assignment.repository.CourseEntityRepository;
import com.springboot.assignment.repository.StudentEntityRepository;

@Repository
public class EducationalInstituteDao {
	
	@Autowired
	private StudentEntityRepository theStudentEntityRepository;
	
	@Autowired
	private CourseEntityRepository theCourseEntityRepository;

	public List<StudentEntity> fetchAvailableStudents() {
		return theStudentEntityRepository.findAll();
	}

	public StudentEntity createStudentWithRegisteredCourses(final StudentEntity transitEntity) {
		return theStudentEntityRepository.saveAndFlush(transitEntity);
	}

	public Optional<StudentEntity> getStudent(final Long id) {
		return theStudentEntityRepository.findById(id);
	}

	public void modifyStudent(final StudentEntity studentEntity) {
		theStudentEntityRepository.saveAndFlush(studentEntity);
	}

	public void removeStudent(final Long id) {
		theStudentEntityRepository.deleteById(id);
	}

	public List<CourseEntity> fetchAvailableCourses() {
		return theCourseEntityRepository.findAll();
	}

	public CourseEntity createCourse(CourseEntity transitEntity) {
		return theCourseEntityRepository.saveAndFlush(transitEntity);
	}

	public Optional<CourseEntity> getCourse(Long id) {
		return theCourseEntityRepository.findById(id);
	}

	public void modifyCourse(CourseEntity courseEntity) {
		theCourseEntityRepository.saveAndFlush(courseEntity);
	}

	public void removeCourse(Long id) {
		theCourseEntityRepository.deleteById(id);
	}

	public Optional<CourseEntity> getNamedCourse(String courseName) {
		return Optional.ofNullable(theCourseEntityRepository.findByCourseName(courseName));
	}

}
