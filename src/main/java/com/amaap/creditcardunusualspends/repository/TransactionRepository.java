package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.service.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionRepository {

    boolean addTransactionData(long creditCardNumber, Transaction transaction);

     List<Transaction> getTransactionDataFor(Long creditCardNumber);
}
