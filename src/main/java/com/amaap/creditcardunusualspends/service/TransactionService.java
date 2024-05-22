package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;

public class TransactionService {
     private final CreditCardRepository creditCardRepository;
     private  final Transaction transaction;

    public TransactionService(CreditCardRepository creditCardRepository, Transaction transaction) {
        this.creditCardRepository = creditCardRepository;
        this.transaction = transaction;
    }

    public  boolean perFromTransaction() {
        return transaction.performTransactionFor(creditCardRepository.GetCreditCardNumber());
    }
}
