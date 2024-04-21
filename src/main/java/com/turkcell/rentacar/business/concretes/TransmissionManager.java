package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedTransmissionResponse;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    private final TransmissionRepository transmissionRepository;
    private final TransmissionBusinessRules transmissionBusinessRules;
    private final ModelMapperManager modelMapperService;

    @Override
    public CreatedTransmissionResponse add(CreatedTransmissionRequest createdTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(createdTransmissionRequest.getName());

        Transmission transmission =
                this.modelMapperService.forRequest().map(createdTransmissionRequest, Transmission.class);

        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse =
                this.modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);

        return createdTransmissionResponse;
    }

    @Override
    public UpdatedTransmissionResponse update(UpdatedTransmissionRequest updatedTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(updatedTransmissionRequest.getName());
        transmissionBusinessRules.transmissionShouldBeExist(updatedTransmissionRequest.getId());

        Transmission existingTransmission = this.modelMapperService.forRequest().map(updatedTransmissionRequest, Transmission.class);

        existingTransmission.setUpdatedDate(LocalDateTime.now());
        Transmission transmission = transmissionRepository.save(existingTransmission);

        UpdatedTransmissionResponse updatedTransmissionResponse =
                this.modelMapperService.forResponse().map(transmission, UpdatedTransmissionResponse.class);

        return updatedTransmissionResponse;
    }

    @Override
    public void delete(int id) {
        transmissionBusinessRules.transmissionShouldBeExist(id);
        Transmission existingTransmission = transmissionRepository.findById(id).orElse(null);
        transmissionRepository.delete(existingTransmission);
    }

    @Override
    public GetTransmissionResponse getById(int id) {
        transmissionBusinessRules.transmissionShouldBeExist(id);
        Transmission transmission = transmissionRepository.findById(id).orElse(null);
        GetTransmissionResponse getTransmissionResponse = this.modelMapperService.forResponse().map(transmission, GetTransmissionResponse.class);
        return getTransmissionResponse;
    }

    @Override
    public List<GetAllTransmissionResponse> getAll() {
        List<GetAllTransmissionResponse> transmissionResponses = new ArrayList<>();
        List<Transmission> transmissions = transmissionRepository.findAll();
        for (Transmission transmission : transmissions) {
            GetAllTransmissionResponse getTransmissionResponse =
                    this.modelMapperService.forResponse().map(transmission, GetAllTransmissionResponse.class);
            transmissionResponses.add(getTransmissionResponse);
        }
        return transmissionResponses;
    }

}
