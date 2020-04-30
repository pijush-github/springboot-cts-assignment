package com.springboot.assignment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.Student;
import com.springboot.assignment.service.EducationalInstituteService;

@RestController
@RequestMapping("/api/educationalinstitute")
public class EducationalInstituteController {

	@Autowired
	private EducationalInstituteService theEducationalInstituteService;
	
	
	@GetMapping(value = {"/candidates"})
	public List<Student> getListOfLocalities(){
		return theEducationalInstituteService.getAllStudentsRegisteredWithCourse();
	}

	@PostMapping(value = {"/candidates"})
	public void addLocality(@RequestBody Student student){
		theEducationalInstituteService.addStudentRegisteredWithCourses(student);
	}
	
	@PutMapping(value = {"/candidates/{id}"})
	public void modifyLocality(@RequestBody Student student, @PathVariable String id){
		theEducationalInstituteService.modifyStudent(student, id);
	}
	
	@DeleteMapping(value = {"/candidates/{id}"})
	public Boolean removeLocality(@PathVariable String id){
		return theEducationalInstituteService.removeStudentFromRegisteredCourses(id);
	}
}
