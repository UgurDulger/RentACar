package com.turkcell.rentacar.business.dtos.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedMaintenanceRequest {
    private int carId;
}
