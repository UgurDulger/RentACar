package com.turkcell.rentacar.business.dtos.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCustomerResponse {
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
