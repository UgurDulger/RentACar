package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/individualCustomers")
public class IndividualCustomersController {
    private IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreatedIndividualCustomerRequest createdIndividualCustomerRequest) {
        return individualCustomerService.add(createdIndividualCustomerRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@RequestBody UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest) {
        return individualCustomerService.update(updatedIndividualCustomerRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable int id) {
        return individualCustomerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllIndividualCustomerResponse> getAll() {
        return individualCustomerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        individualCustomerService.delete(id);
    }

}
