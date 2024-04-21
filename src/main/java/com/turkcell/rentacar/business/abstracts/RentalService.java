package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedRentalResponse;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse add(CreatedRentalRequest createdRentalRequest);

    UpdatedRentalResponse update(UpdatedRentalRequest updatedRentalRequest);

    void delete(int id);

    GetRentalResponse getById(int id);

    List<GetAllRentalResponse> getAll();
}
