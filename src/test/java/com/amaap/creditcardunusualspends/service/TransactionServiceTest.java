package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    TransactionService transactionService;
    CreditCardRepository creditCardRepository;
    CreditCardService creditCardService;
    Transaction transaction;

    @BeforeEach
    void setUp()
    {
        Injector injector= Guice.createInjector(new UserModule());
        creditCardRepository=injector.getInstance(CreditCardRepository.class);
        creditCardService=injector.getInstance(CreditCardService.class);
        transaction=injector.getInstance(Transaction.class);
        transactionService=new TransactionService(creditCardRepository,transaction);
    }
    @Test
    void shouldBeAbleToReturnTrueIfTransactionIsStartedForTheCredCardNumber() throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange
        creditCardService.CreateCard(12345678);
        // act
        assertTrue(transactionService.perFromTransaction());

    }
}