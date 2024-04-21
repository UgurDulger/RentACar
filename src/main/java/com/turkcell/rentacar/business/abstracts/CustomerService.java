package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCustomerResponse;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponse add(CreatedCustomerRequest createdCustomerRequest);
    void delete(int id);
    GetCustomerResponse getById(int id);
    List<GetAllCustomerResponse> getAll();
}
