package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedPaymentResponse;

import java.util.List;

public interface PaymentService {
    CreatedPaymentResponse add(CreatedPaymentRequest createdPaymentRequest);

    UpdatedPaymentResponse update(UpdatedPaymentRequest updatedPaymentRequest);

    void delete(int id);

    List<GetAllPaymentResponse> getAll();

    GetPaymentResponse getById(int id);
}
