package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.google.inject.Inject;

import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private Database database;

    @Inject
    public InMemoryTransactionRepository(Database database) {
        this.database = database;
    }

    @Override
    public void addIntoTransactionData(Transaction transaction) {
        database.insertIntoTransactionTable(transaction);
    }

    @Override
    public List<Transaction> getTransactionData() {
        return database.getTransactions();
    }
}
