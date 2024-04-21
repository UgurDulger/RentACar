package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.CarMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    CarRepository carRepository;

    public void checkCarAvailability(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (!car.get().getState().equals(CarState.AVAILABLE)) {
            throw new BusinessException(CarMessages.carIsNotAvailable);
        }
    }

    public void carShouldBeExist(int carId) {
        Optional<Car> foundOptionalCar = carRepository.findById(carId);
        if (foundOptionalCar.isEmpty()) {
            throw new BusinessException(CarMessages.carNotFound);
        }
    }

}
