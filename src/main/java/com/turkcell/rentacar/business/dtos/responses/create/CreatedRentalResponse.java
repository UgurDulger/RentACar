package com.turkcell.rentacar.business.dtos.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedRentalResponse {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime returnDate;
    private int carId;
    private LocalDateTime createdDate;
    private int customerId;

}
