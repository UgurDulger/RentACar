package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedTransmissionResponse;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreatedTransmissionRequest createdTransmissionRequest);

    UpdatedTransmissionResponse update(UpdatedTransmissionRequest updatedTransmissionRequest);

    void delete(int id);

    List<GetAllTransmissionResponse> getAll();

    GetTransmissionResponse getById(int id);
}
