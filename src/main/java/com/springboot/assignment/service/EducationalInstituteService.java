package com.springboot.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.assignment.dao.EducationalInstituteDao;
import com.springboot.assignment.entity.StudentEntity;
import com.springboot.assignment.model.Student;
import com.springboot.assignment.util.StudentEntityModelConverter;

@Service
public class EducationalInstituteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationalInstituteService.class);

	@Autowired
	private EducationalInstituteDao theEducationalInstituteDao;
	
	@Autowired
	private StudentEntityModelConverter studentEntityModelConverter;

	public List<Student> getAllStudentsRegisteredWithCourse() {
		LOGGER.info("EducationalInstituteService.getAllStudentsRegisteredWithCourse() invocation started.");
		final List<StudentEntity> savedStudents = theEducationalInstituteDao.fetchAvailableStudents();
		return savedStudents.stream().map(s -> studentEntityModelConverter.convertToModel(s)).collect(Collectors.toList());
	}

	public Boolean addStudentRegisteredWithCourses(Student student) {
		LOGGER.info("EducationalInstituteService.getAllStudentsRegisteredWithCourse() invocation started.");
		final StudentEntity transitEntity = studentEntityModelConverter.convertToEntity(student);
		final StudentEntity savedEntity = theEducationalInstituteDao.createWithRegisteredCourses(transitEntity);
		return OptionalLong.of(savedEntity.getId()).isPresent();
	}

	public void modifyStudent(Student student, String id) {
		LOGGER.info("EducationalInstituteService.modifyStudent() invocation started.");
		final StudentEntity transitEntity = studentEntityModelConverter.convertToEntity(student);
		final Optional<StudentEntity> savedLocalityEntity = theEducationalInstituteDao.getStudent(Long.valueOf(id));
		if(savedLocalityEntity.isPresent()) {
			savedLocalityEntity.get().setFirstName(student.getFirstName());
			savedLocalityEntity.get().setLastName(student.getLastName());
			savedLocalityEntity.get().getCourses().clear();
			transitEntity.getCourses().forEach(c -> savedLocalityEntity.get().getCourses().add(c));
			theEducationalInstituteDao.modifyStudent(savedLocalityEntity.get());
		}
	}

	public Boolean removeStudentFromRegisteredCourses(String id) {
		LOGGER.info("EducationalInstituteService.removeStudentFromRegisteredCourses() invocation started.");
		theEducationalInstituteDao.removeStusent(Long.valueOf(id));
		return theEducationalInstituteDao.getStudent(Long.valueOf(id)).isPresent();
	}

}
