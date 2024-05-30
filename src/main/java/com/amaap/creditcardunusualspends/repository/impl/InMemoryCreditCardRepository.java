package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.google.inject.Inject;

import java.util.List;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private Database database;

    @Inject
    public InMemoryCreditCardRepository(Database database) {
        this.database = database;
    }

    @Override
    public void addCreditCardData(CreditCard creditCard) {
        database.insertIntoCreditCardData(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCards() {
        return database.getCreditCardData();
    }
}
