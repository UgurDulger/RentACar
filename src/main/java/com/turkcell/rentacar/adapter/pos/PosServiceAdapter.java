package com.turkcell.rentacar.adapter.pos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class PosServiceAdapter implements PosService {
    @Override
    public boolean pay(String cardNumber, String cardHolder, String cvv, String expiryYearDate, String expiryMonthDate, double totalPrice) {
        Random random = new Random();
        return random.nextBoolean();
    }
}
