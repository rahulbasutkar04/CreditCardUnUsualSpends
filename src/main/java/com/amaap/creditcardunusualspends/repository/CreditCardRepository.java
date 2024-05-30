package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;

import java.util.List;

public interface CreditCardRepository {

    void addCreditCardData(CreditCard creditCard);

    List<CreditCard> getCreditCards();


    boolean isCreditCardPresent(Long creditCardNumber);
}
