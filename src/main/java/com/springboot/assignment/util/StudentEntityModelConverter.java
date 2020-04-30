package com.springboot.assignment.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.assignment.entity.CourseEntity;
import com.springboot.assignment.entity.StudentEntity;
import com.springboot.assignment.model.Course;
import com.springboot.assignment.model.Student;

@Component
public class StudentEntityModelConverter implements EntityModelConverter<StudentEntity, Student> {

	@Override
	public StudentEntity convertToEntity(Student model) {
		StudentEntity entity = new StudentEntity();
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		if(Optional.of(model.getCourses()).isPresent()) {
			model.getCourses().stream().forEach(m -> {
				CourseEntity ce = new CourseEntity();
				ce.setCourseName(m.getCourseName());
				ce.setCourseDuration(Integer.valueOf(m.getCourseDuration()));
				ce.getStudents().add(entity);
				entity.getCourses().add(ce);
			});
		}
		return entity;
	}

	@Override
	public Student convertToModel(StudentEntity entity) {
		Student model = new Student();
		model.setId(String.valueOf(entity.getId()));
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		List<Course> courses = entity.getCourses().stream().map(e -> {
			return new Course(String.valueOf(e.getId()), String.valueOf(e.getCourseName()),
					String.valueOf(e.getCourseDuration()), null);
		}).collect(Collectors.toList());
		model.setCourses(courses);
		return model;
	}

}
