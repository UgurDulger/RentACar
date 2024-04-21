package com.turkcell.rentacar.business.dtos.responses.create;

import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private int modelId;
    private LocalDateTime createdDate;
    private CarState state;
    private String brandName;
    private String fuelName;
    private String transmissionName;
    private int minFindexScore;

}
