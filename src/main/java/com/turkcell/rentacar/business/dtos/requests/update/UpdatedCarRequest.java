package com.turkcell.rentacar.business.dtos.requests.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCarRequest {
    private int id;

    @NotNull
    private int modelYear;

    @NotNull
    private String plate;

    @NotNull
    @Min(50)
    @Max(300000)
    private double dailyPrice;

    @NotNull
    private int modelId;

    @NotNull
    @Min(1)
    @Max(1900)
    private int minFindexScore;
}
