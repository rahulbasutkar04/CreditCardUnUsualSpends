package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.Transaction;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.google.inject.Inject;

import java.util.Date;

public class TransactionService {
    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;


    @Inject
    public TransactionService(CreditCardRepository creditCardRepository, TransactionRepository transactionRepository) {
        this.creditCardRepository = creditCardRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean performTransaction(Date date, Categories categories, Double amount) throws IllegalAmountException {
        long creditCardNumber = creditCardRepository.getCreditCardNumber();//whos credit cared...
        // u cannot perform transaction without the credit card
        Transaction transaction =Transaction.createTransaction(date, categories, amount);
        return transactionRepository.addTransactionData(creditCardNumber, transaction);
    }
}
