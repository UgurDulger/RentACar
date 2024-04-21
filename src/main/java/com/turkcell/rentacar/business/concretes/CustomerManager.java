package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCustomerResponse;
import com.turkcell.rentacar.business.rules.CustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private CustomerBusinessRules customerRules;

    @Override
    public CreatedCustomerResponse add(CreatedCustomerRequest createdCustomerRequest) {
        Customer customer = modelMapperService.forRequest().map(createdCustomerRequest, Customer.class);

        customer.setCreatedDate(LocalDateTime.now());
        Customer createdCustomer = customerRepository.save(customer);

        return modelMapperService.forResponse().map(createdCustomer, CreatedCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        customerRules.customerShouldBeExist(id);
        customerRepository.deleteById(id);
    }

    @Override
    public GetCustomerResponse getById(int id) {
        customerRules.customerShouldBeExist(id);
        Customer customer = customerRepository.findById(id).orElse(null);
        GetCustomerResponse getCustomerResponse = this.modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return getCustomerResponse;
    }

    @Override
    public List<GetAllCustomerResponse> getAll() {
        //TODO MERTCAN ANLATACAK
        List<Customer> customers = customerRepository.findAll();
        return modelMapperService.forResponse().map(customers, new TypeToken<List<GetCustomerResponse>>() {
        }.getType());
    }
}

