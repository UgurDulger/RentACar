package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedFuelResponse;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;

    private ModelMapperService modelMapperService;

    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreatedFuelRequest createdFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(createdFuelRequest.getName());
        Fuel createdFuel = this.modelMapperService.forRequest().map(createdFuelRequest, Fuel.class);
        createdFuel.setCreatedDate(LocalDateTime.now());
        fuelRepository.save(createdFuel);

        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse()
                .map(createdFuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public UpdatedFuelResponse update(UpdatedFuelRequest updatedFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(updatedFuelRequest.getName());
        fuelBusinessRules.fuelShouldBeExist(updatedFuelRequest.getId());

        Fuel existingFuel = this.modelMapperService.forRequest().map(updatedFuelRequest, Fuel.class);
        existingFuel.setUpdatedDate(LocalDateTime.now());
        Fuel updatedFuel = fuelRepository.save(existingFuel);
        UpdatedFuelResponse updatedFuelResponse =
                this.modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);

        return updatedFuelResponse;
    }

    @Override
    public void delete(int id) {
        fuelBusinessRules.fuelShouldBeExist(id);
        fuelRepository.deleteById(id);
    }

    @Override
    public GetFuelResponse getById(int id) {
        fuelBusinessRules.fuelShouldBeExist(id);
        Optional<Fuel> fuel = fuelRepository.findById(id);
        GetFuelResponse getFuelResponse =
                this.modelMapperService.forResponse().map(fuel, GetFuelResponse.class);
        fuelBusinessRules.fuelShouldBeExist(id);
        return getFuelResponse;
    }

    @Override
    public List<GetAllFuelResponse> getAll() {
        List<GetAllFuelResponse> fuelResponses = new ArrayList<>();
        List<Fuel> fuels = fuelRepository.findAll();
        for (Fuel fuel : fuels) {
            GetAllFuelResponse fuelResponse =
                    this.modelMapperService.forResponse().map(fuel, GetAllFuelResponse.class);
            fuelResponses.add(fuelResponse);
        }
        return fuelResponses;
    }
}
