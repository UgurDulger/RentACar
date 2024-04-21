package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllModelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedModelResponse;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreatedModelRequest createdModelRequest);

    UpdatedModelResponse update(UpdatedModelRequest updatedModelRequest);

    void delete(int id);

    List<GetAllModelResponse> getAll();

    GetModelResponse getById(int id);
}
