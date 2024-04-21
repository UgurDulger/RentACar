package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.CompanyCustomerMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CompanyCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CompanyCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@AllArgsConstructor
public class CompanyCustomerBusinessRules {
    private final CompanyCustomerRepository companyCustomerRepository;

    public void brandNameCanNotBeDuplicated(String companyCustomerName) {
        Optional<CompanyCustomer> companyCustomer = companyCustomerRepository.findByCompanyNameIgnoreCase(companyCustomerName);
        if (companyCustomer.isPresent()) {
            throw new BusinessException(CompanyCustomerMessages.companyCustomerAlreadyExists);
        }
    }

    public void companyCustomerShouldBeExist(int companyCustomerId) {
        Optional<CompanyCustomer> foundOptionalCompanyCustomer = companyCustomerRepository.findById(companyCustomerId);
        if (foundOptionalCompanyCustomer.isEmpty()) {
            throw new BusinessException(CompanyCustomerMessages.companyCustomerNotFound);
        }
    }

}
