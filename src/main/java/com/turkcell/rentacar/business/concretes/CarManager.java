package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCarRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCarRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCarResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCarResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCarResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private ModelBusinessRules modelBusinessRules;

    @Override
    public CreatedCarResponse add(CreatedCarRequest createCarRequest) {
        modelBusinessRules.modelIdShouldBeExist(createCarRequest.getModelId());
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

        car.setCreatedDate(LocalDateTime.now());
        car.setState(CarState.AVAILABLE); //İlk defa eklenen araba direkt kullanılabilir.
        Car createdCar = carRepository.save(car);
        CreatedCarResponse createdCarResponse = this.modelMapperService.forResponse().map(createdCar, CreatedCarResponse.class);

        return createdCarResponse;
    }

    @Override
    public UpdatedCarResponse update(UpdatedCarRequest updatedCarRequest) {
        carBusinessRules.carShouldBeExist(updatedCarRequest.getId());
        carBusinessRules.checkCarAvailability(updatedCarRequest.getId());

        Car car = this.modelMapperService.forRequest().map(updatedCarRequest, Car.class);
        car.setUpdatedDate(LocalDateTime.now());

        carRepository.save(car);
        return this.modelMapperService.forResponse().map(car, UpdatedCarResponse.class);
    }

    @Override
    public void delete(int id) {
        carBusinessRules.carShouldBeExist(id);
        carRepository.deleteById(id);
    }

    @Override
    public GetCarResponse getById(int id) {
        carBusinessRules.carShouldBeExist(id);
        Optional<Car> foundOptionalCar = carRepository.findById(id);
        GetCarResponse getCarResponse = this.modelMapperService.forResponse().map(foundOptionalCar, GetCarResponse.class);
        return getCarResponse;
    }

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> carsResponse = new ArrayList<>();
        for (var car : cars) {
            GetAllCarResponse getAllCarResponse = this.modelMapperService.forResponse().map(car, GetAllCarResponse.class);
            carsResponse.add(getAllCarResponse);
        }
        return carsResponse;
    }
}



