package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreatedBrandRequest createBrandRequest);

    UpdatedBrandResponse update(UpdatedBrandRequest updatedBrandRequest);

    void delete(int id);

    GetBrandResponse getById(int id);

    List<GetAllBrandResponse> getAll();

}

