package com.turkcell.rentacar.business.dtos.responses.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCustomerResponse {
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
