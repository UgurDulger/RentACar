package com.turkcell.rentacar.business.dtos.requests.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedIndividualCustomerRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11)
    private String identityNo;
}