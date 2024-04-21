package com.turkcell.rentacar.entities.concretes;


import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companyCustomers")
public class CompanyCustomer extends BaseEntity {

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "taxNo")
    private String taxNo;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
