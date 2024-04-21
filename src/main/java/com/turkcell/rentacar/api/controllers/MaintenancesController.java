package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedMaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/maintenances")
@RequiredArgsConstructor
public class MaintenancesController {
    private final MaintenanceService maintenanceService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedMaintenanceResponse add(@RequestBody CreatedMaintenanceRequest createMaintenanceRequest){
        return this.maintenanceService.add(createMaintenanceRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllMaintenanceResponse> getAll(){
        return this.maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetMaintenanceResponse getById(@PathVariable int id){
        return this.maintenanceService.getById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UpdatedMaintenanceResponse update(@RequestBody UpdatedMaintenanceRequest updateMaintenanceRequest){
        return this.maintenanceService.update(updateMaintenanceRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id){ maintenanceService.delete(id);}

}
