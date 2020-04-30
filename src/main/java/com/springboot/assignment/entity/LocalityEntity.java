package com.springboot.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "locality")
@Entity(name = "LocalityEntity")
public class LocalityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "street_name")
	private String street;
	@Column(name = "city_name")
	private String city;
	@OneToMany(mappedBy = "localityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter(AccessLevel.NONE)
	private Set<WaterTankEntity> waterTanks = new HashSet<>();
	
	
	public void addWaterTank(final WaterTankEntity inWaterTank) {
        this.waterTanks.add(inWaterTank);
        inWaterTank.setLocalityEntity(this);
    }
 
    public void removeWaterTank(final WaterTankEntity inWaterTank) {
    	this.waterTanks.remove(inWaterTank);
        inWaterTank.setLocalityEntity(null);
    }
    
    public void removeAllWaterTank() {
    	this.waterTanks.stream().forEach(wt -> wt.setLocalityEntity(null));
    	waterTanks.clear();
    }
}
