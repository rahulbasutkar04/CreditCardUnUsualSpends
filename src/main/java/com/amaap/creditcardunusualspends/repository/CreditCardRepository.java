package com.amaap.creditcardunusualspends.repository;

import java.util.Map;

public interface CreditCardRepository {
    void addCreditCardDetails(int userId, long creditCardNumber);

    Map<Integer, Long> getCreditCardDetails();

    long GetCreditCardNumber();

    long getCreditCardNumberByUserId(int userId);
}
