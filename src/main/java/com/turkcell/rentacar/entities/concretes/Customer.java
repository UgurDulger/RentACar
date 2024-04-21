package com.turkcell.rentacar.entities.concretes;


import com.turkcell.rentacar.core.entities.BaseEntity;
import com.turkcell.rentacar.entities.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "type")
    private CustomerType type;

    private int findexScore;

    @OneToOne(mappedBy = "customer")
    private IndividualCustomer individualCustomer;

    @OneToOne(mappedBy = "customer")
    private CompanyCustomer companyCustomer;

    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;

}
