package com.turkcell.rentacar.business.dtos.requests.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedModelRequest {
    @NotNull
    private int id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    private int brandId;
    @NotNull
    private int fuelId;
    @NotNull
    private int transmissionId;
}
