package com.springboot.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@Entity(name = "CourseEntity")
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@NaturalId
	@Column(name = "course_name")
	private String courseName;
	@Column(name = "course_duration")
	private Integer courseDuration;
	
	@ManyToMany (mappedBy = "courses")
	private Set<StudentEntity> students = new HashSet<>();
	
	@Override
    public int hashCode() {
    	final int PRIME = 31; int hashCode = 1;
    	hashCode = (PRIME * hashCode) + (id == null ? 21 : id.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (courseName == null ? 93 : courseName.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (courseDuration == null ? 71 : courseDuration.hashCode() * hashCode);
        return hashCode;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseEntity )) return false;
        boolean idEquals = id != null && id.equals(((CourseEntity) o).getId());
        boolean courseNameEquals = courseName != null && courseName.equals(((CourseEntity) o).getCourseName());
        boolean courseDurationEquals = courseDuration != null && courseDuration.equals(((CourseEntity) o).getCourseDuration());
        return idEquals && courseNameEquals && courseDurationEquals;
    }
}
