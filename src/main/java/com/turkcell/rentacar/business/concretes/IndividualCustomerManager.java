package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapter.findex.FindexService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.concretes.IndividualCustomer;
import com.turkcell.rentacar.entities.enums.CustomerType;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private ModelMapperService modelMapperService;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private CustomerService customerService;
    private FindexService findexService;

    @Override
    public CreatedIndividualCustomerResponse add(CreatedIndividualCustomerRequest createdIndividualCustomerRequest) {
        CreatedCustomerResponse createdCustomerResponse = customerService.add(new CreatedCustomerRequest(CustomerType.INDIVIDUAL));
        Customer customer = modelMapperService.forResponse().map(createdCustomerResponse, Customer.class);
        int findexScore = findexService.getFindexScoreForIndividualCustomer(createdIndividualCustomerRequest.getIdentityNo());
        customer.setFindexScore(findexScore);

        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createdIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setCreatedDate(LocalDateTime.now());
        individualCustomer.setCustomer(customer);


        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);
        return modelMapperService.forResponse().map(createdIndividualCustomer, CreatedIndividualCustomerResponse.class);
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest) {
        individualCustomerBusinessRules.individualCustomerIdShouldBeExist(updatedIndividualCustomerRequest.getId());

        IndividualCustomer individualCustomerToUpdate = modelMapperService.forRequest().map(updatedIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomerToUpdate.setId(updatedIndividualCustomerRequest.getId());

        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomerToUpdate);

        return modelMapperService.forResponse().map(updatedIndividualCustomer, UpdatedIndividualCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        individualCustomerBusinessRules.individualCustomerIdShouldBeExist(id);
        individualCustomerRepository.deleteById(id);
    }

    @Override
    public GetIndividualCustomerResponse getById(int id) {
        individualCustomerBusinessRules.individualCustomerIdShouldBeExist(id);
        Optional<IndividualCustomer> foundOptionalIndividualCustomer = individualCustomerRepository.findById(id);
        return modelMapperService.forResponse().map(foundOptionalIndividualCustomer.get(), GetIndividualCustomerResponse.class);
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll() {
        //TODO MERTCAN BİZE ANLATACAK
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
        return modelMapperService.forResponse().map(individualCustomers, new TypeToken<List<GetAllIndividualCustomerResponse>>() {
        }.getType());
    }
}
