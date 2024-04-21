package com.turkcell.rentacar.business.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalResponse {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime returnDate;
    private int carId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int customerId;
}
