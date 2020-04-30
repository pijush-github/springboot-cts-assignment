package com.springboot.assignment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.assignment.entity.LocalityEntity;
import com.springboot.assignment.entity.WaterTankEntity;
import com.springboot.assignment.repository.LocalityEntityRepository;
import com.springboot.assignment.repository.WaterTankEntityRepository;

@Repository
public class MunicipalityDao {

	@Autowired
	private LocalityEntityRepository theLocalityEntityRepository;
	
	@Autowired
	private WaterTankEntityRepository theWaterTankEntityRepository;

	public List<LocalityEntity> fetchAvailableLocalities() {
		return theLocalityEntityRepository.findAll();
	}

	public LocalityEntity createLocality(LocalityEntity entity) {
		return theLocalityEntityRepository.saveAndFlush(entity);
	}

	public Optional<LocalityEntity> getLocality(Long id) {
		return theLocalityEntityRepository.findById(id);
	}

	public void removeLocality(Long id) {
		theLocalityEntityRepository.deleteById(id);
	}

	public Optional<WaterTankEntity> getWaterTank(Long id) {
		return theWaterTankEntityRepository.findById(id);
	}

	public void removeWaterTank(Long id) {
		theWaterTankEntityRepository.deleteById(id);
	}

	public void modifyLocality(LocalityEntity localityEntity) {
		theLocalityEntityRepository.saveAndFlush(localityEntity);
	}

	public void createWaterTank(LocalityEntity localityEntity) {
		theLocalityEntityRepository.saveAndFlush(localityEntity);
	}

	public void modifyWaterTank(WaterTankEntity waterTankEntity) {
		theWaterTankEntityRepository.saveAndFlush(waterTankEntity);
	}
}
