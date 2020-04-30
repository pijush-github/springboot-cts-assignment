package com.springboot.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	private String id;
	private String courseName;
	private String courseDuration;
	private List<Student> students;
}
