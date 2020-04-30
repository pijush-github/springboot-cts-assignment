package com.springboot.assignment.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.assignment.entity.LocalityEntity;
import com.springboot.assignment.entity.WaterTankEntity;
import com.springboot.assignment.model.Locality;
import com.springboot.assignment.model.WaterTank;

@Component
public class LocalityEntityModelConverter implements EntityModelConverter<LocalityEntity, Locality> {

	@Override
	public LocalityEntity convertToEntity(Locality model) {
		LocalityEntity entity = new LocalityEntity();
		entity.setStreet(model.getStreet());
		entity.setCity(model.getCity());
		if(Optional.of(model.getWaterTanks()).isPresent()) {
			model.getWaterTanks().stream().map(m -> {
				return new WaterTankEntity(null, Double.valueOf(m.getCapacity()), LocalDate.parse(m.getLastCleanedOn()),
						entity);
			}).forEach(we -> entity.addWaterTank(we));
		}
		return entity;
	}

	@Override
	public Locality convertToModel(LocalityEntity entity) {
		Locality model = new Locality();
		model.setId(String.valueOf(entity.getId()));
		model.setStreet(entity.getStreet());
		model.setCity(entity.getCity());
		List<WaterTank> waterTanks = entity.getWaterTanks().stream().map(e -> {
			return new WaterTank(String.valueOf(e.getId()), String.valueOf(e.getCapacity()),
					String.valueOf(e.getLastCleanedOn()));
		}).collect(Collectors.toList());
		model.setWaterTanks(waterTanks);
		return model;
	}

}
