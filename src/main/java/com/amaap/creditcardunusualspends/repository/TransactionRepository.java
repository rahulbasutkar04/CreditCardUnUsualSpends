package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    void addIntoTransactionData(Transaction transaction);

    List<Transaction> getTransactionData();
}
