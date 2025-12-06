package com.example.javazon.enums;

public enum PaymentMethod {
    CASH("Cash"),
    CARD("Card");


    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return paymentMethod;
    }
}
