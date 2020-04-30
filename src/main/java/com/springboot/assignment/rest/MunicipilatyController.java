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

import com.springboot.assignment.model.Locality;
import com.springboot.assignment.model.WaterTank;
import com.springboot.assignment.service.MunicipalityService;

@RestController
@RequestMapping("/api/municipalty")
public class MunicipilatyController {
	
	@Autowired
	private MunicipalityService theMunicipalityService;
	
	@GetMapping(value = {"/localities"})
	public List<Locality> getListOfLocalities(){
		return theMunicipalityService.getAllLocalitiesUnderMuincipality();
	}

	@PostMapping(value = {"/localities"})
	public void addLocality(@RequestBody Locality locality){
		theMunicipalityService.addLocalityUnderMuincipality(locality);
	}
	
	@PutMapping(value = {"/localities/{id}"})
	public void modifyLocality(@RequestBody Locality locality, @PathVariable String id){
		theMunicipalityService.modifyExistingLocalityUnderMuincipality(locality, id);
	}
	
	@PatchMapping(value = {"/localities/{parent}/{child}"})
	public void partialyModifyLocality(@RequestBody WaterTank tank, @PathVariable String parent, @PathVariable String child){
		theMunicipalityService.addOrModifyWaterTankUnderExistingLocality(tank, parent, child);
	}
	
	@DeleteMapping(value = {"/localities/{id}"})
	public Boolean removeLocality(@PathVariable String id){
		return theMunicipalityService.removeExistingLocalityFromMuincipality(id);
	}
	
	@DeleteMapping(value = {"/localities/{parent}/{child}"})
	public Boolean addLocality(@PathVariable String parent, @PathVariable String child){
		return theMunicipalityService.removeWaterTankFromLocality(parent, child);
	}
}
