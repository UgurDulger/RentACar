package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedBrandResponse;
import com.turkcell.rentacar.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service   //101
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    private ModelMapperService modelMapperService;

    private BrandBusinessRules brandBussinessRules;

    @Override
    public CreatedBrandResponse add(CreatedBrandRequest createBrandRequest) {
        brandBussinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);

        CreatedBrandResponse createdBrandResponse =
                this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public UpdatedBrandResponse update(UpdatedBrandRequest updatedBrandRequest) {
        brandBussinessRules.brandNameCanNotBeDuplicated(updatedBrandRequest.getName());
        brandBussinessRules.brandShouldBeExist(updatedBrandRequest.getId());

        Brand existingBrand = this.modelMapperService.forRequest().map(updatedBrandRequest, Brand.class);

        existingBrand.setUpdatedDate(LocalDateTime.now());
        Brand updatedBrand = brandRepository.save(existingBrand);

        UpdatedBrandResponse updatedBrandResponse =
                this.modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);

        return updatedBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandBussinessRules.brandShouldBeExist(id);
        brandRepository.deleteById(id);
    }

    @Override
    public GetBrandResponse getById(int id) {
        brandBussinessRules.brandShouldBeExist(id);
        Optional<Brand> brand = brandRepository.findById(id);
        GetBrandResponse getBrandResponse = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<GetAllBrandResponse> brandResponses = new ArrayList<>();
        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            GetAllBrandResponse brandResponse =
                    this.modelMapperService.forResponse().map(brand, GetAllBrandResponse.class);
            brandResponses.add(brandResponse);
        }
        return brandResponses;
    }
}
