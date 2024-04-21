package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.CustomerMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    public void customerShouldBeExist(int customerId) {
        Optional<Customer> foundOptionalCustomer = customerRepository.findById(customerId);
        if (foundOptionalCustomer.isEmpty()) {
            throw new BusinessException(CustomerMessages.customerNotFound);
        }
    }
}
