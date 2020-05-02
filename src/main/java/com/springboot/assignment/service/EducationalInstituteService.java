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
import com.springboot.assignment.entity.CourseEntity;
import com.springboot.assignment.entity.StudentEntity;
import com.springboot.assignment.model.Course;
import com.springboot.assignment.model.Student;
import com.springboot.assignment.util.CourseEntityModelConverter;
import com.springboot.assignment.util.StudentEntityModelConverter;

@Service
public class EducationalInstituteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EducationalInstituteService.class);

	@Autowired
	private EducationalInstituteDao theEducationalInstituteDao;

	@Autowired
	private StudentEntityModelConverter studentEntityModelConverter;

	@Autowired
	private CourseEntityModelConverter courseEntityModelConverter;

	public List<Student> getAllStudentsAlongWithRegisteredCourses() {
		LOGGER.info("EducationalInstituteService.getAllStudents() invocation started.");
		final List<StudentEntity> savedStudents = theEducationalInstituteDao.fetchAvailableStudents();
		return savedStudents.stream().map(s -> studentEntityModelConverter.convertToModel(s))
				.collect(Collectors.toList());
	}

	public Boolean registerNewStudentAlongWithNewCourses(Student student) {
		LOGGER.info("EducationalInstituteService.getAllStudentsRegisteredWithCourse() invocation started.");
		final StudentEntity transitEntity = studentEntityModelConverter.convertToEntity(student);
		final StudentEntity savedEntity = theEducationalInstituteDao.createStudentWithRegisteredCourses(transitEntity);
		return OptionalLong.of(savedEntity.getId()).isPresent();
	}

	public void modifyStudent(Student student, String id) {
		LOGGER.info("EducationalInstituteService.modifyStudent() invocation started.");
		final StudentEntity transitEntity = studentEntityModelConverter.convertToEntity(student);
		final Optional<StudentEntity> savedStudentEntity = theEducationalInstituteDao.getStudent(Long.valueOf(id));
		if (savedStudentEntity.isPresent()) {
			savedStudentEntity.get().setFirstName(student.getFirstName());
			savedStudentEntity.get().setLastName(student.getLastName());
			if (Optional.ofNullable(student.getCourses()).isPresent()) {
				transitEntity.getCourses().forEach(c -> savedStudentEntity.get().addCourse(c));
			} else {
				savedStudentEntity.get().removeAllCourse();
			}
			theEducationalInstituteDao.modifyStudent(savedStudentEntity.get());
		}
	}

	public Boolean removeStudentFromRegisteredCourses(String id) {
		LOGGER.info("EducationalInstituteService.removeStudentFromRegisteredCourses() invocation started.");
		Optional<StudentEntity> savedStudentEntity = theEducationalInstituteDao.getStudent(Long.valueOf(id));
		if(savedStudentEntity.isPresent()) {
			savedStudentEntity.get().removeAllCourse();
			theEducationalInstituteDao.removeStudent(Long.valueOf(id));
		}
		return theEducationalInstituteDao.getStudent(Long.valueOf(id)).isPresent();
	}

	public List<Course> getAllCoursesAlongWithRegisteredStudents() {
		LOGGER.info("EducationalInstituteService.getAllCourses() invocation started.");
		final List<CourseEntity> savedCourses = theEducationalInstituteDao.fetchAvailableCourses();
		return savedCourses.stream().map(c -> courseEntityModelConverter.convertToModel(c))
				.collect(Collectors.toList());
	}

	public Boolean addCourse(Course course) {
		LOGGER.info("EducationalInstituteService.addCourse() invocation started.");
		final CourseEntity transitEntity = courseEntityModelConverter.convertToEntity(course);
		final CourseEntity savedEntity = theEducationalInstituteDao.createCourse(transitEntity);
		return OptionalLong.of(savedEntity.getId()).isPresent();

	}

	public void modifyCourseHours(Course course, String id) {
		LOGGER.info("EducationalInstituteService.modifyCourse() invocation started.");
		final Optional<CourseEntity> savedCourseEntity = theEducationalInstituteDao.getCourse(Long.valueOf(id));
		if (savedCourseEntity.isPresent()) {
			savedCourseEntity.get().setCourseDuration(Integer.valueOf(course.getCourseDuration()));
			theEducationalInstituteDao.modifyCourse(savedCourseEntity.get());
		}
	}

	public Boolean removeCourse(String id) {
		LOGGER.info("EducationalInstituteService.removeCourse() invocation started.");
		Optional<CourseEntity> savedCourseEntity = theEducationalInstituteDao.getCourse(Long.valueOf(id));
		if(savedCourseEntity.isPresent()) {
			savedCourseEntity.get().getStudents().forEach(s -> {s.removeCourse(savedCourseEntity.get());});
			theEducationalInstituteDao.removeCourse(Long.valueOf(id));
		}
		return theEducationalInstituteDao.getCourse(Long.valueOf(id)).isPresent();
	}

	public void deregisterCourseForExistingStudent(String id, String role) {
		LOGGER.info("EducationalInstituteService.deregisterCourseForExistingStudent() invocation started.");
		Optional<CourseEntity> savedCourseEntity = theEducationalInstituteDao.getCourse(Long.valueOf(id));
		Optional<StudentEntity> savedStudentEntity = theEducationalInstituteDao.getStudent(Long.valueOf(id));
		if(savedStudentEntity.isPresent() && savedCourseEntity.isPresent()) {
			savedStudentEntity.get().removeCourse(savedCourseEntity.get());
		}
		theEducationalInstituteDao.modifyStudent(savedStudentEntity.get());
	}

	public void enroleStudentUnderExistingCourses(List<Course> course, String id) {
		LOGGER.info("EducationalInstituteService.deregisterCourseForExistingStudent() invocation started.");
		Optional<StudentEntity> savedStudentEntity = theEducationalInstituteDao.getStudent(Long.valueOf(id));
		course.stream().forEach(c -> { 
			Optional<CourseEntity> savedCourseEntity = theEducationalInstituteDao.getNamedCourse(c.getCourseName());
			if(savedStudentEntity.isPresent() && savedCourseEntity.isPresent()) {
				savedStudentEntity.get().addCourse(savedCourseEntity.get());
			}
		});
		theEducationalInstituteDao.modifyStudent(savedStudentEntity.get());
	}

}
