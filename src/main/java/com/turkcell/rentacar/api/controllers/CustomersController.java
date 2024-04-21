package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomersController {
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCustomerResponse add(@Valid @RequestBody CreatedCustomerRequest createdCustomerRequest) {
        return customerService.add(createdCustomerRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        customerService.delete(id);
    }
}
