package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandsController {
    private BrandService brandService; //101

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreatedBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBrandResponse update(@RequestBody UpdatedBrandRequest updatedBrandRequest) {
        return brandService.update(updatedBrandRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBrandResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAll() {
        return brandService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        brandService.delete(id);
    }
}
