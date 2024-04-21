package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapter.findex.FindexService;
import com.turkcell.rentacar.business.abstracts.CompanyCustomerService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedCompanyCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllCompanyCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedCompanyCustomerResponse;
import com.turkcell.rentacar.business.rules.CompanyCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CompanyCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CompanyCustomer;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.enums.CustomerType;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyCustomerManager implements CompanyCustomerService {

    private CompanyCustomerRepository companyCustomerRepository;
    private ModelMapperService modelMapperService;
    private CompanyCustomerBusinessRules companyCustomerBusinessRules;
    private CustomerService customerService;
    private FindexService findexService;

    /*bir müşteri oluşturma işleminin gerçekleştirilmesini ve sonucunda oluşturulan müşterinin bilgilerini içeren
    bir yanıt nesnesini döndürme işlemini sağlar. Müşteri oluşturma işlemi, istemciden gelen istek verilerinin alınması,
     müşteri nesnesine dönüştürülmesi, oluşturulma tarihinin ayarlanması ve veritabanına kaydedilmesi adımlarını içerir. Sonuç olarak,
      oluşturulan müşterinin bilgileri, istemciye döndürülen bir yanıt nesnesi aracılığıyla geri gönderilir.
     */
    @Override
    public CreatedCompanyCustomerResponse add(CreatedCompanyCustomerRequest createdCompanyCustomerRequest) {
        companyCustomerBusinessRules.brandNameCanNotBeDuplicated(createdCompanyCustomerRequest.getCompanyName());
        CreatedCustomerResponse createdCustomerResponse = customerService.add(new CreatedCustomerRequest(CustomerType.COMPANY));
        Customer customer = modelMapperService.forResponse().map(createdCustomerResponse, Customer.class);

        int findexScore = findexService.getFindexScoreForCompanyCustomer(createdCompanyCustomerRequest.getTaxNo());
        customer.setFindexScore(findexScore);

        CompanyCustomer companyCustomer = modelMapperService.forRequest().map(createdCompanyCustomerRequest, CompanyCustomer.class);
        companyCustomer.setCreatedDate(LocalDateTime.now());
        companyCustomer.setCustomer(customer);


        CompanyCustomer createdCompanyCustomer = companyCustomerRepository.save(companyCustomer);
        return modelMapperService.forResponse().map(createdCompanyCustomer, CreatedCompanyCustomerResponse.class);

    }

    @Override
    public UpdatedCompanyCustomerResponse update(UpdatedCompanyCustomerRequest updatedCompanyCustomerRequest) {
        companyCustomerBusinessRules.brandNameCanNotBeDuplicated(updatedCompanyCustomerRequest.getCompanyName());
        companyCustomerBusinessRules.companyCustomerShouldBeExist(updatedCompanyCustomerRequest.getId());

        CompanyCustomer companyCustomer = this.modelMapperService.forRequest().map(updatedCompanyCustomerRequest, CompanyCustomer.class);

        companyCustomer.setUpdatedDate(LocalDateTime.now());
        CompanyCustomer updatedCompanyCustomer = companyCustomerRepository.save(companyCustomer);
        return this.modelMapperService.forResponse().map(updatedCompanyCustomer, UpdatedCompanyCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        companyCustomerBusinessRules.companyCustomerShouldBeExist(id);
        companyCustomerRepository.deleteById(id);
    }

    @Override
    public GetCompanyCustomerResponse getById(int id) {
        companyCustomerBusinessRules.companyCustomerShouldBeExist(id);
        Optional<CompanyCustomer> companyCustomer = companyCustomerRepository.findById(id);
        GetCompanyCustomerResponse getCompanyCustomerResponse = this.modelMapperService.forResponse().map(companyCustomer, GetCompanyCustomerResponse.class);
        return getCompanyCustomerResponse;

    }

    @Override
    public List<GetAllCompanyCustomerResponse> getAll() {
        //TODO MERTCAN BURAYI BİZE ANLATSIN
        List<CompanyCustomer> companyCostumers = companyCustomerRepository.findAll();
        return modelMapperService.forResponse().map(companyCostumers, new TypeToken<List<GetAllCompanyCustomerResponse>>() {
        }.getType());
    }
}
