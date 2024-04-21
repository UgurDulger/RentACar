package com.turkcell.rentacar.business.dtos.responses.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMaintenanceResponse {
    private int id;
    private int carId;
    private LocalDateTime sendData;
    private LocalDateTime createdDate;
    private LocalDateTime returnDate;
}
