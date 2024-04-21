package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedPaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPaymentResponse add(@Valid @RequestBody CreatedPaymentRequest createdPaymentRequest) {
        return paymentService.add(createdPaymentRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedPaymentResponse update(@RequestBody UpdatedPaymentRequest updatedPaymentRequest) {
        return paymentService.update(updatedPaymentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        paymentService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetPaymentResponse getById(int id) {
        return paymentService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllPaymentResponse> getAll() {
        return paymentService.getAll();
    }


}
