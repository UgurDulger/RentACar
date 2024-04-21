package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedPaymentResponse;
import com.turkcell.rentacar.business.rules.PaymentRules;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    PaymentRepository paymentRepository;
    ModelMapperService modelMapperService;
    CustomerRepository customerRepository;
    PaymentRules paymentRules;
    RentalBusinessRules rentalBusinessRules;
    RentalRepository rentalRepository;


    @Override
    public CreatedPaymentResponse add(CreatedPaymentRequest createdPaymentRequest) {
        paymentRules.checkPayment(createdPaymentRequest);

        Payment payment = this.modelMapperService.forRequest().map(createdPaymentRequest, Payment.class);
        Rental rental = rentalRepository.findById(payment.getRental().getId()).orElse(null);
        payment.setTotalPrice(rentalBusinessRules.calculateTotalPrice(rental));

        payment.setCreatedDate(LocalDateTime.now());
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);

        Customer customer = customerRepository.findById(rental.getCustomer().getId()).orElse(null);

        CreatedPaymentResponse createdPaymentResponse =this.modelMapperService.forResponse().map(payment, CreatedPaymentResponse.class);
        createdPaymentResponse.setCustomerName(paymentRules.findCustomerNameByCustomerType(customer));

        return createdPaymentResponse;
    }

    @Override
    public UpdatedPaymentResponse update(UpdatedPaymentRequest updatedPaymentRequest) {
        paymentRules.paymentShouldBeExist(updatedPaymentRequest.getId());

        Payment existingPayment = modelMapperService.forRequest().map(updatedPaymentRequest, Payment.class);
        Rental rental = rentalRepository.findById(existingPayment.getRental().getId()).orElse(null);

        existingPayment.setTotalPrice(rentalBusinessRules.calculateTotalPrice(rental));
        existingPayment.setUpdatedDate(LocalDateTime.now());
        paymentRepository.save(existingPayment);

        UpdatedPaymentResponse updatedPaymentResponse = modelMapperService.forResponse().map(existingPayment, UpdatedPaymentResponse.class);

        Customer customer = customerRepository.findById(rental.getCustomer().getId()).orElse(null);

        updatedPaymentResponse.setCustomerName(paymentRules.findCustomerNameByCustomerType(customer));
        updatedPaymentResponse.setUpdatedDate(LocalDateTime.now());

        return updatedPaymentResponse;
    }

    @Override
    public void delete(int id) {
        paymentRules.paymentShouldBeExist(id);
        paymentRepository.deleteById(id);
    }

    @Override
    public List<GetAllPaymentResponse> getAll() {
        List<GetAllPaymentResponse> paymentResponses = new ArrayList<>();
        List<Payment> payments = paymentRepository.findAll();
        for (Payment payment : payments) {
            GetAllPaymentResponse paymentResponse =
                    this.modelMapperService.forResponse().map(payment, GetAllPaymentResponse.class);
            paymentResponses.add(paymentResponse);
        }
        return paymentResponses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        Rental rental = rentalRepository.findById(payment.getRental().getId()).orElse(null);

        Customer customer = customerRepository.findById(rental.getCustomer().getId()).orElse(null);
        GetPaymentResponse getPaymentResponse = this.modelMapperService.forResponse().map(payment, GetPaymentResponse.class);

        getPaymentResponse.setCustomerName(paymentRules.findCustomerNameByCustomerType(customer));

        return getPaymentResponse;
    }
}
