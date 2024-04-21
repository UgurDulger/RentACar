package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreatedIndividualCustomerRequest createdIndividualCustomerRequest);

    UpdatedIndividualCustomerResponse update(UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest);

    void delete(int id);

    GetIndividualCustomerResponse getById(int id);

    List<GetAllIndividualCustomerResponse> getAll();
}
