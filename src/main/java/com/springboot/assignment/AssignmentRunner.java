package com.springboot.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AssignmentRunner {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentRunner.class, args);
	}

}
