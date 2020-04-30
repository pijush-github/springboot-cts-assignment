package com.springboot.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String id;
	private String firstName;
	private String lastName;
	private List<Course> courses;
}
