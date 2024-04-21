package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "additional_features")
public class AdditionalFeature extends BaseEntity {

    private String name;

    private double featurePrice;

    private String description;

    @ManyToMany(mappedBy = "additionalFeatures")
    private List<Rental> rentals;

}
