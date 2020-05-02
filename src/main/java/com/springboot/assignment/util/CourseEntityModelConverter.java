package com.springboot.assignment.util;

import java.util.List;
import java.util.stream.Collectors;

import com.springboot.assignment.entity.CourseEntity;
import com.springboot.assignment.model.Course;
import com.springboot.assignment.model.Student;

public class CourseEntityModelConverter implements EntityModelConverter<CourseEntity, Course> {

	@Override
	public CourseEntity convertToEntity(Course model) {
		CourseEntity entity = new CourseEntity();
		entity.setId(Long.valueOf(model.getId()));
		entity.setCourseName(model.getCourseName());
		entity.setCourseDuration(Integer.valueOf(model.getCourseDuration()));
		return entity;
	}

	@Override
	public Course convertToModel(CourseEntity entity) {
		Course model = new Course();
		model.setId(String.valueOf(entity.getId()));
		model.setCourseName(entity.getCourseName());
		model.setCourseDuration(entity.getCourseName());
		List<Student> students = entity.getStudents().stream().map(s -> {
			return new Student(String.valueOf(s.getId()), s.getFirstName(), s.getLastName(), null);
		}).collect(Collectors.toList());
		model.setStudents(students);
		return model;
	}

}
