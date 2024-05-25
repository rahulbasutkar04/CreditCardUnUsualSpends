package com.amaap.creditcardunusualspends.service.exception;

public class InvalidCreditCardNumber extends  CreditCardException {
    public InvalidCreditCardNumber(String message) {
        super(message);
    }
}
