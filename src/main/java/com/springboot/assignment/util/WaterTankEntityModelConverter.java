package com.springboot.assignment.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.springboot.assignment.entity.WaterTankEntity;
import com.springboot.assignment.model.WaterTank;

@Component
public class WaterTankEntityModelConverter implements EntityModelConverter<WaterTankEntity, WaterTank> {

	@Override
	public WaterTankEntity convertToEntity(WaterTank model) {
		WaterTankEntity entity = new WaterTankEntity();
		entity.setCapacity(Double.valueOf(model.getCapacity()));
		entity.setLastCleanedOn(LocalDate.parse(model.getLastCleanedOn()));		
		return entity;
	}

	@Override
	public WaterTank convertToModel(WaterTankEntity entity) {
		WaterTank model = new WaterTank();
		model.setId(String.valueOf(entity.getId()));
		model.setCapacity(String.valueOf(entity.getCapacity()));
		model.setLastCleanedOn(String.valueOf(entity.getLastCleanedOn()));
		return model;
	}

}
