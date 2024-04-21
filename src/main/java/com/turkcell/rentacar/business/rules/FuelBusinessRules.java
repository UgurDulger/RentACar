package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.FuelMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelBusinessRules {
    FuelRepository fuelRepository;

    public void fuelNameCanNotBeDuplicated(String fuelName) {
        Optional<Fuel> fuel = fuelRepository.findByNameIgnoreCase(fuelName);
        if (fuel.isPresent()) {
            throw new BusinessException(FuelMessages.fuelAlreadyExists);
        }
    }

    public void fuelShouldBeExist(int fuelId) {
        Optional<Fuel> foundOptionalFuel = fuelRepository.findById(fuelId);
        if (foundOptionalFuel.isEmpty()) {
            throw new BusinessException(FuelMessages.fuelNotFound);
        }
    }
}
