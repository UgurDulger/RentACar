package com.turkcell.rentacar.business.dtos.responses.get;

import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCarResponse {
    private int id;
    private int findexScore;
    private int modelYear;
    private String plate;
    private CarState state;
    private int dailyPrice;
    private String brandName;
    private String fuelName;
    private String transmissionName;
    private LocalDateTime createdDate;
    private int minFindexScore;

}