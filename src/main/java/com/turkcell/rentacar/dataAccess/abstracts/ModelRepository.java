package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    Optional<Model> findByNameIgnoreCase(String fuelName);
}
