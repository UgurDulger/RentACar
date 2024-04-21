package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedCarRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCarRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCarResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCarResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCarResponse;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreatedCarRequest createCarRequest);

    GetCarResponse getById(int id);

    List<GetAllCarResponse> getAll();

    UpdatedCarResponse update(UpdatedCarRequest updatedCarRequest);

    void delete(int id);


}
