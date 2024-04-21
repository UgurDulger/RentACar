package com.turkcell.rentacar.business.dtos.requests.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedRentalRequest {
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private int carId;

    @NotNull
    private int customerId;

    private List<Integer> additionalFeatures;
}
