package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.BrandMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    BrandRepository brandRepository;

    public void brandNameCanNotBeDuplicated(String brandName) {
        Optional<Brand> brand = brandRepository.findByNameIgnoreCase(brandName);
        if (brand.isPresent()) {
            throw new BusinessException(BrandMessages.brandAlreadyExists);
        }
    }

    public void brandShouldBeExist(int brandId) {
        Optional<Brand> foundOptionalBrand = brandRepository.findById(brandId);
        if (foundOptionalBrand.isEmpty()) {
            throw new BusinessException(BrandMessages.brandNotFound);
        }
    }

}
