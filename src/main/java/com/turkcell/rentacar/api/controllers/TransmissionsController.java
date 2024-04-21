package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@Valid @RequestBody CreatedTransmissionRequest createdTransmissionRequest) {
        return transmissionService.add(createdTransmissionRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedTransmissionResponse update(@RequestBody UpdatedTransmissionRequest updatedTransmissionRequest) {
        return transmissionService.update(updatedTransmissionRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        transmissionService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTransmissionResponse> getAll() {
        return transmissionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTransmissionResponse getById(@PathVariable int id) {
        return transmissionService.getById(id);
    }
}
