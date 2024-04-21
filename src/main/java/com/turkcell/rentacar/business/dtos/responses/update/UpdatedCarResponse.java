package com.turkcell.rentacar.business.dtos.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private int modelId;
    private LocalDateTime createdDate;
    private int minFindexScore;
}
