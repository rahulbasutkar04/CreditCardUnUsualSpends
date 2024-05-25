package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    boolean addTransactionData(long creditCardNumber, Transaction transaction);

    List<Transaction> getTransactionDataFor(Long creditCardNumber);
}
