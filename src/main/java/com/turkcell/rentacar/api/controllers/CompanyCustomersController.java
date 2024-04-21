package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CompanyCustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCompanyCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/companyCustomers")
public class CompanyCustomersController {
    private CompanyCustomerService companyCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCompanyCustomerResponse add(@Valid @RequestBody CreatedCompanyCustomerRequest createdCompanyCustomerRequest) {
        return companyCustomerService.add(createdCompanyCustomerRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCompanyCustomerResponse update(@RequestBody UpdatedCompanyCustomerRequest updatedCompanyCustomerBrandRequest) {
        return companyCustomerService.update(updatedCompanyCustomerBrandRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCompanyCustomerResponse getById(@PathVariable int id) {
        return companyCustomerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCompanyCustomerResponse> getAll() {
        return companyCustomerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        companyCustomerService.delete(id);
    }
}
