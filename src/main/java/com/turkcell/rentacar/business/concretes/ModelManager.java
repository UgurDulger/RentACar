package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllModelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private BrandBusinessRules brandBusinessRules;
    private FuelBusinessRules fuelBusinessRules;
    private TransmissionBusinessRules transmissionBusinessRules;


    @Override
    public CreatedModelResponse add(CreatedModelRequest createdModelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(createdModelRequest.getName());
        brandBusinessRules.brandShouldBeExist(createdModelRequest.getBrandId());
        fuelBusinessRules.fuelShouldBeExist(createdModelRequest.getFuelId());
        transmissionBusinessRules.transmissionShouldBeExist(createdModelRequest.getTransmissionId());

        Model createdModel = this.modelMapperService.forRequest().map(createdModelRequest, Model.class);
        createdModel.setCreatedDate(LocalDateTime.now());
        modelRepository.save(createdModel);

        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public UpdatedModelResponse update(UpdatedModelRequest updatedModelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(updatedModelRequest.getName());
        modelBusinessRules.modelIdShouldBeExist(updatedModelRequest.getId());
        brandBusinessRules.brandShouldBeExist(updatedModelRequest.getBrandId());
        fuelBusinessRules.fuelShouldBeExist(updatedModelRequest.getFuelId());
        transmissionBusinessRules.transmissionShouldBeExist(updatedModelRequest.getTransmissionId());

        Model existingModel = modelMapperService.forRequest().map(updatedModelRequest, Model.class);
        existingModel.setUpdatedDate(LocalDateTime.now());
        Model updatedModel = modelRepository.save(existingModel);

        UpdatedModelResponse updatedModelResponse = modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    public void delete(int id) {
        modelBusinessRules.modelIdShouldBeExist(id);
        modelRepository.deleteById(id);
    }

    @Override
    public GetModelResponse getById(int id) {
        modelBusinessRules.modelIdShouldBeExist(id);
        Optional<Model> model = modelRepository.findById(id);
        GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
        return getModelResponse;
    }

    @Override
    public List<GetAllModelResponse> getAll() {
        List<GetAllModelResponse> modelResponses = new ArrayList<>();
        List<Model> models = modelRepository.findAll();
        for (Model model : models) {
            GetAllModelResponse modelResponse =
                    this.modelMapperService.forResponse().map(model, GetAllModelResponse.class);
            modelResponses.add(modelResponse);
        }
        return modelResponses;
    }
}
