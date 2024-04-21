package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import com.turkcell.rentacar.entities.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private double totalPrice;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDate;
}
