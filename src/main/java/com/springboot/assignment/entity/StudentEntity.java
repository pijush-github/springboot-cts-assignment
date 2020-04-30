package com.springboot.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@Entity(name = "StudentEntity")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
	})
	@JoinTable(name = "student_course",
				joinColumns = @JoinColumn(name= "student_id"), 
				inverseJoinColumns =  @JoinColumn(name ="course_id"))
	private Set<CourseEntity> courses = new HashSet<>();
	
	
	@Override
    public int hashCode() {
    	final int PRIME = 91; int hashCode = 1;
    	hashCode = (PRIME * hashCode) + (id == null ? 21 : id.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (firstName == null ? 93 : firstName.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (lastName == null ? 71 : lastName.hashCode() * hashCode);
        return hashCode;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentEntity )) return false;
        boolean idEquals = id != null && id.equals(((StudentEntity) o).getId());
        boolean firstNameEquals = firstName != null && firstName.equals(((StudentEntity) o).getFirstName());
        boolean lastNameEquals = lastName != null && lastName.equals(((StudentEntity) o).getLastName());
        return idEquals && firstNameEquals && lastNameEquals;
    }
}
