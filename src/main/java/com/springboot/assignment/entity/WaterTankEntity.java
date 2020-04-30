package com.springboot.assignment.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "water_tank")
@Entity(name = "WaterTankEntity")
public class WaterTankEntity {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
    private Long id;
	@Column(name = "capacity")
    private Double capacity;
	@Column(name = "last_cleaned_on")
    private LocalDate lastCleanedOn;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private LocalityEntity localityEntity;
     
 
    @Override
    public int hashCode() {
    	final int PRIME = 51; int hashCode = 1;
    	hashCode = (PRIME * hashCode) + (id == null ? 37 : id.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (capacity == null ? 43 : capacity.hashCode() * hashCode);
    	hashCode = (PRIME * hashCode) + (lastCleanedOn == null ? 57 : lastCleanedOn.hashCode() * hashCode);
        return hashCode;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaterTankEntity )) return false;
        boolean idEquals = id != null && id.equals(((WaterTankEntity) o).getId());
        boolean capacityEquals = capacity != null && capacity.equals(((WaterTankEntity) o).getCapacity());
        boolean lastCleanedOnEquals = lastCleanedOn != null && lastCleanedOn.equals(((WaterTankEntity) o).getLastCleanedOn());
        return idEquals && capacityEquals && lastCleanedOnEquals;
    }
}
