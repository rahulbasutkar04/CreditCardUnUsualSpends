package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Inject;

import java.util.Map;

public class InMemoryCreditCardRepository implements CreditCardRepository {

    private final FakeDatabase fakeDatabase;

    @Inject
    public InMemoryCreditCardRepository(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    @Override
    public void addCreditCardDetails(int userId, long creditCardNumber) {
        fakeDatabase.InsertIntoCreditCardTable(userId, creditCardNumber);
    }

    @Override
    public Map<Integer, Long> getCreditCardDetails() {
        return fakeDatabase.getCreditCardData();
    }

    @Override
    public long GetCreditCardNumber() {
        return fakeDatabase.getCreditCardNumber();
    }

    @Override
    public long getCreditCardNumberByUserId(int userId) {
        return fakeDatabase.getCreditCardNumberById(userId);
    }
}
