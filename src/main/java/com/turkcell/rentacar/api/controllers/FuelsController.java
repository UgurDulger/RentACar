package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedFuelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
public class FuelsController {
    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@RequestBody CreatedFuelRequest createdFuelRequest) {
        return fuelService.add(createdFuelRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedFuelResponse update(@RequestBody UpdatedFuelRequest updatedFuelRequest) {
        return fuelService.update(updatedFuelRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFuelResponse getById(@PathVariable int id) {
        return fuelService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelResponse> getAll() {
        return fuelService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        fuelService.delete(id);
    }
}
