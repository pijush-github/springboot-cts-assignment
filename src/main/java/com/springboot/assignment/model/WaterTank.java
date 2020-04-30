package com.springboot.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterTank {

    private String id;
    private String capacity;
    private String lastCleanedOn;
}
