package com.springboot.assignment.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locality {

	private String id;
	private String street;
	private String city;
	private List<WaterTank> waterTanks;
}
