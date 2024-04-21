package com.turkcell.rentacar.business.dtos.requests.create;


import com.turkcell.rentacar.entities.enums.CustomerType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCustomerRequest {

    @NotNull
    private CustomerType type;

}
