package com.turkcell.rentacar.business.dtos.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedMaintenanceResponse {
    private int id;
    private int carId;
    private LocalDateTime sendData;
    private LocalDateTime createdDate;
    private LocalDateTime returnDate;
}
