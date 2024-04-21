package com.turkcell.rentacar.api.controllers;


import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCarRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCarRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCarResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCarResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
public class CarsController {
    private CarService carService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse add(@Valid @RequestBody CreatedCarRequest createdCarRequest) {
        return carService.add(createdCarRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCarResponse update(@RequestBody UpdatedCarRequest updatedCarRequest) {
        return carService.update(updatedCarRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCarResponse> getAll() {
        return carService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        carService.delete(id);
    }

}
