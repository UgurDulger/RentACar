package com.turkcell.rentacar.api.controllers;


import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllModelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelsController {
    ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@RequestBody CreatedModelRequest createdModelRequest) {
        return modelService.add(createdModelRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedModelResponse update(@RequestBody UpdatedModelRequest updatedModelRequest) {
        return modelService.update(updatedModelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        modelService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelResponse getById(int id) {
        return modelService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAll() {
        return modelService.getAll();
    }

}
