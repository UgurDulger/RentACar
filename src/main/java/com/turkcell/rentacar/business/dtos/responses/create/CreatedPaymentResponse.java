package com.turkcell.rentacar.business.dtos.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedPaymentResponse {
    private int id ;
    private String customerName;
    private LocalDateTime createdDate;
    private int totalPrice;
    private LocalDateTime paymentDate;

}
