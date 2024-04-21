package com.turkcell.rentacar.business.dtos.responses.getAll;

import com.turkcell.rentacar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllIndividualCustomerResponse {
    private String firstName;
    private String lastName;
    private String identityNo;
    private double findexScore;
    private Customer customer;
    private LocalDateTime createdDate;
    private LocalDateTime uptadedDate;
}
