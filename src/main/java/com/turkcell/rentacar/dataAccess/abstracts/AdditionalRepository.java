package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.AdditionalFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalRepository  extends JpaRepository<AdditionalFeature,Integer> {
}
