package com.springboot.assignment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.assignment.dao.MunicipalityDao;
import com.springboot.assignment.entity.LocalityEntity;
import com.springboot.assignment.entity.WaterTankEntity;
import com.springboot.assignment.model.Locality;
import com.springboot.assignment.model.WaterTank;
import com.springboot.assignment.util.LocalityEntityModelConverter;
import com.springboot.assignment.util.WaterTankEntityModelConverter;

@Service
public class MunicipalityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalityService.class);

	@Autowired
	private MunicipalityDao theMunicipalityDao;
	
	@Autowired
	private LocalityEntityModelConverter localityEntityConverter;
	
	@Autowired
	private WaterTankEntityModelConverter waterTankEntityConverter;

	public List<Locality> getAllLocalitiesUnderMuincipality() {
		LOGGER.info("MunicipalityService.getAllLocalitiesUnderMuincipality() invocation started.");
		final List<LocalityEntity> localities = theMunicipalityDao.fetchAvailableLocalities();
		return localities.stream().map(le -> localityEntityConverter.convertToModel(le)).collect(Collectors.toList());
	}

	public Boolean addLocalityUnderMuincipality(Locality locality) {
		LOGGER.info("MunicipalityService.addLocalityUnderMuincipality() invocation started.");
		final LocalityEntity transitEntity = localityEntityConverter.convertToEntity(locality);
		final LocalityEntity savedEntity = theMunicipalityDao.createLocality(transitEntity);
		return OptionalLong.of(savedEntity.getId()).isPresent();
	}

	public void modifyExistingLocalityUnderMuincipality(Locality locality, String id) {
		LOGGER.info("MunicipalityService.addLocalityUnderMuincipality() invocation started.");
		final LocalityEntity transitEntity = localityEntityConverter.convertToEntity(locality);
		final Optional<LocalityEntity> savedLocalityEntity = theMunicipalityDao.getLocality(Long.valueOf(id));
		if(savedLocalityEntity.isPresent()) {
			savedLocalityEntity.get().setStreet(locality.getStreet());
			savedLocalityEntity.get().setCity(locality.getCity());
			savedLocalityEntity.get().removeAllWaterTank();
			transitEntity.getWaterTanks().forEach(w -> savedLocalityEntity.get().addWaterTank(w));
			theMunicipalityDao.modifyLocality(savedLocalityEntity.get());
		}
	}

	public void addOrModifyWaterTankUnderExistingLocality(WaterTank tank, String parent, String child) {
		LOGGER.info("MunicipalityService.addWaterTankUnderExistingLocality() invocation started.");
		final Optional<LocalityEntity> savedLocalityEntity = theMunicipalityDao.getLocality(Long.valueOf(parent));
		final Optional<WaterTankEntity> savedWaterTankEntity = theMunicipalityDao.getWaterTank(Long.valueOf(child));
		if(savedLocalityEntity.isPresent()) {
			if(savedWaterTankEntity.isPresent()) {
				savedWaterTankEntity.get().setCapacity(Double.valueOf(tank.getCapacity()));
				savedWaterTankEntity.get().setLastCleanedOn(LocalDate.parse(tank.getLastCleanedOn()));
				theMunicipalityDao.modifyWaterTank(savedWaterTankEntity.get());
			}else {
				WaterTankEntity transitTankEntity = waterTankEntityConverter.convertToEntity(tank);
				savedLocalityEntity.get().addWaterTank(transitTankEntity);
				theMunicipalityDao.createWaterTank(savedLocalityEntity.get());
			}
		}
	}

	public Boolean removeExistingLocalityFromMuincipality(String id) {
		LOGGER.info("MunicipalityService.removeExistingLocalityFromMuincipality() invocation started.");
		theMunicipalityDao.removeLocality(Long.valueOf(id));
		return theMunicipalityDao.getLocality(Long.valueOf(id)).isPresent();
	}

	public Boolean removeWaterTankFromLocality(String parent, String child) {
		LOGGER.info("MunicipalityService.removeWaterTankFromLocality() invocation started.");
		final Optional<LocalityEntity> savedLocalityEntity = theMunicipalityDao.getLocality(Long.valueOf(parent));
		final Optional<WaterTankEntity> savedWaterTankEntity = theMunicipalityDao.getWaterTank(Long.valueOf(child));
		if(savedLocalityEntity.isPresent() && savedWaterTankEntity.isPresent() && savedLocalityEntity.get().getWaterTanks().contains(savedWaterTankEntity.get())) {
			savedLocalityEntity.get().removeWaterTank(savedWaterTankEntity.get());
			/*
			 * Need to revisit this call as the relationship owner is locality.
			 * theMunicipalityDao.removeWaterTank(savedLocalityEntity); can save the entity.
			 * deletion of water tank may happen automatically
			 */
			theMunicipalityDao.removeWaterTank(Long.valueOf(child));
		}
		return theMunicipalityDao.getWaterTank(Long.valueOf(child)).isPresent();
	}

}
