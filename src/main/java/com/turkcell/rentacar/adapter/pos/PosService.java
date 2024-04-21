package com.turkcell.rentacar.adapter.pos;

public interface PosService {
    boolean pay(String cardNumber, String cardHolder, String cvv, String expiryYearDate, String expiryMonthDate, double totalPrice);
}
