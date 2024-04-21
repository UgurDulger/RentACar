package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentalsController {
    private RentalService rentalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalResponse add(@Valid @RequestBody CreatedRentalRequest createdRentalRequest) {
        return rentalService.add(createdRentalRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedRentalResponse update(@RequestBody UpdatedRentalRequest updatedRentalRequest) {
        return rentalService.update(updatedRentalRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetRentalResponse getById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalResponse> getAll() {
        return rentalService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        rentalService.delete(id);
    }
}
