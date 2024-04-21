package com.turkcell.rentacar.business.dtos.responses.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private int modelId;
    private LocalDateTime createdDate;
    private int minFindexScore;

}