package com.turkcell.rentacar.business.dtos.requests.create;

import com.turkcell.rentacar.entities.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedPaymentRequest {
    @NotNull
    private int rentalId;
    @NotNull
    private PaymentMethod paymentMethod;
    @Size(min = 16, max = 16)
    private String cardNumber;
    private String cardHolder;
    @Size(min = 3, max = 3)
    private String cvv;
    private String expiryYearDate;
    @Size(min = 1, max = 12)
    private String expiryMonthDate;

}
