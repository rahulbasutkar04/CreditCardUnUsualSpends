package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final FakeDatabase fakeDatabase;

    @Inject
    public InMemoryTransactionRepository(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    @Override
    public boolean addTransactionData(long creditCardNumber, Transaction transaction) {
        return fakeDatabase.insertIntoTransactionTable(creditCardNumber,transaction);
    }

    @Override
    public List<Transaction> getTransactionDataFor(Long creditCardNumber) {
        return fakeDatabase.getTransactionData(creditCardNumber);
    }
}
