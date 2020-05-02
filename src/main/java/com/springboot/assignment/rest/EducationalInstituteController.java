package com.springboot.assignment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.Course;
import com.springboot.assignment.model.Student;
import com.springboot.assignment.service.EducationalInstituteService;

@RestController
@RequestMapping("/api/educationalinstitute")
public class EducationalInstituteController {

	@Autowired
	private EducationalInstituteService theEducationalInstituteService;
	
	
	@GetMapping(value = {"/candidates"})
	public List<Student> getListOfStudents(){
		return theEducationalInstituteService.getAllStudentsAlongWithRegisteredCourses();
	}
	
	@GetMapping(value = {"/courses"})
	public List<Course> getListOfCourses(){
		return theEducationalInstituteService.getAllCoursesAlongWithRegisteredStudents();
	}

	@PostMapping(value = {"/candidates"})
	public void addStudent(@RequestBody Student student){
		theEducationalInstituteService.registerNewStudentAlongWithNewCourses(student);
	}
	
	@PostMapping(value = {"/courses"})
	public void addCourse(@RequestBody Course course){
		theEducationalInstituteService.addCourse(course);
	}
	
	@PutMapping(value = {"/candidates/{id}"})
	public void modifyStudent(@RequestBody Student student, @PathVariable String id){
		theEducationalInstituteService.modifyStudent(student, id);
	}
	
	@PutMapping(value = {"/courses/{id}"})
	public void modifyCourse(@RequestBody Course course, @PathVariable String id){
		theEducationalInstituteService.modifyCourseHours(course, id);
	}
	
	@PatchMapping(value = {"/courses/{id}/{role}"})
	public void degistereCourse(@PathVariable String id, @PathVariable String role){
		theEducationalInstituteService.deregisterCourseForExistingStudent(id, role);
	}
	
	@PatchMapping(value = {"/candidates/{id}/"})
	public void enroleStudent(@RequestBody List<Course> courses, @PathVariable String id){
		theEducationalInstituteService.enroleStudentUnderExistingCourses(courses, id);
	}
	
	@DeleteMapping(value = {"/candidates/{id}"})
	public Boolean removeStudent(@PathVariable String id){
		return theEducationalInstituteService.removeStudentFromRegisteredCourses(id);
	}
	
	@DeleteMapping(value = {"/courses/{id}"})
	public Boolean removeCourse(@PathVariable String id){
		return theEducationalInstituteService.removeCourse(id);
	}
}
