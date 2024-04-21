package com.turkcell.rentacar.business.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCompanyCustomerResponse {
    private int id;
    private LocalDateTime createdDate;
}
