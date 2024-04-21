package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCompanyCustomerResponse;

import java.util.List;

public interface CompanyCustomerService {
    CreatedCompanyCustomerResponse add(CreatedCompanyCustomerRequest createdCompanyCustomerRequest);
    UpdatedCompanyCustomerResponse update(UpdatedCompanyCustomerRequest updatedCompanyCustomerRequest);
    void delete(int id);
    GetCompanyCustomerResponse getById(int id);
    List<GetAllCompanyCustomerResponse> getAll();
}
