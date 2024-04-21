package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedRentalResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.CustomerBusinessRules;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.AdditionalRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.AdditionalFeature;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private RentalBusinessRules rentalBusinessRules;
    private CarBusinessRules carBusinessRules;
    private CustomerBusinessRules customerBusinessRules;
    private AdditionalRepository additionalRepository;


    @Override
    public CreatedRentalResponse add(CreatedRentalRequest createdRentalRequest) {
        carBusinessRules.carShouldBeExist(createdRentalRequest.getCarId());
        customerBusinessRules.customerShouldBeExist(createdRentalRequest.getCustomerId());
        carBusinessRules.checkCarAvailability(createdRentalRequest.getCarId());
        rentalBusinessRules.customerFindexScoreShouldBeEnough(createdRentalRequest.getCarId(), createdRentalRequest.getCustomerId());

        Rental createdRental = this.modelMapperService.forRequest().map(createdRentalRequest, Rental.class);

        List<AdditionalFeature> additionalFeatures = additionalRepository.findAllById(createdRentalRequest.getAdditionalFeatures());
        createdRental.setAdditionalFeatures(additionalFeatures);
        createdRental.setCreatedDate(LocalDateTime.now());

        rentalRepository.save(createdRental);

        CreatedRentalResponse createdRentalResponse =
                this.modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
        return createdRentalResponse;
    }

    @Override
    public UpdatedRentalResponse update(UpdatedRentalRequest updatedRentalRequest) {
        rentalBusinessRules.rentalShouldBeExist(updatedRentalRequest.getId());
        carBusinessRules.carShouldBeExist(updatedRentalRequest.getCarId());
        customerBusinessRules.customerShouldBeExist(updatedRentalRequest.getCustomerId());

        Rental rental = this.modelMapperService.forRequest().map(updatedRentalRequest, Rental.class);
        rental.setUpdatedDate(LocalDateTime.now());
        Rental updatedRental = rentalRepository.save(rental);

        return this.modelMapperService.forResponse().map(updatedRental, UpdatedRentalResponse.class);
    }

    @Override
    public void delete(int id) {
        rentalBusinessRules.rentalShouldBeExist(id);
        rentalRepository.deleteById(id);
    }

    @Override
    public GetRentalResponse getById(int id) {
        rentalBusinessRules.rentalShouldBeExist(id);
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        Rental rental = rentalOptional.get();
        GetRentalResponse getRentalResponse =
                this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> rentalResponses = new ArrayList<>();

        for (Rental rental : rentals) {
            GetAllRentalResponse getAllRentalResponse =
                    this.modelMapperService.forResponse().map(rental, GetAllRentalResponse.class);
            rentalResponses.add(getAllRentalResponse);
        }
        return rentalResponses;
    }
}
