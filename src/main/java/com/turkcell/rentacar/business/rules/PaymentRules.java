package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.adapter.pos.PosService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.messages.PaymentMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rental;
import com.turkcell.rentacar.entities.enums.CustomerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentRules {
    PaymentRepository paymentRepository;
    PosService posService;
    RentalBusinessRules rentalBusinessRules;
    RentalRepository rentalRepository;

    public String findCustomerNameByCustomerType(Customer customer) {
        if (findCustomerType(customer).equals(CustomerType.INDIVIDUAL)) {
            return customer.getIndividualCustomer().getFirstName() + customer.getIndividualCustomer().getLastName();
        } else {
            return customer.getCompanyCustomer().getCompanyName();
        }
    }

    public CustomerType findCustomerType(Customer customer) {
        return customer.getType();
    }

    public void checkPayment(CreatedPaymentRequest createdPaymentRequest) {
        rentalBusinessRules.rentalShouldBeExist(createdPaymentRequest.getRentalId());
        Rental rental = rentalRepository.findById(createdPaymentRequest.getRentalId()).orElse(null);

        boolean paymentControl = posService.pay(createdPaymentRequest.getCardNumber(),
                createdPaymentRequest.getCardHolder(),
                createdPaymentRequest.getCvv(),
                createdPaymentRequest.getExpiryYearDate(),
                createdPaymentRequest.getExpiryMonthDate(),
                rentalBusinessRules.calculateTotalPrice(rental)
        );

        if (!paymentControl) throw new BusinessException(PaymentMessages.invalidCreditCard);
    }

    public void paymentShouldBeExist(int paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (payment.isEmpty()) {
            throw new BusinessException(PaymentMessages.paymentNotfound);
        }
    }
}
