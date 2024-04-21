package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedMaintenanceResponse;

import java.util.List;


public interface MaintenanceService {
    CreatedMaintenanceResponse add(CreatedMaintenanceRequest createdMaintenanceRequest);

    List<GetAllMaintenanceResponse> getAll();

    UpdatedMaintenanceResponse update(UpdatedMaintenanceRequest updateMaintenanceRequest);

    GetMaintenanceResponse getById(int id);

    void delete(int id);
}
