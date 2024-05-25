package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.Transaction;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTransactionRepositoryTest {
    private InMemoryTransactionRepository transactionRepository;
    private FakeDatabase fakeDatabase;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        fakeDatabase = injector.getInstance(FakeDatabase.class);
        transactionRepository = new InMemoryTransactionRepository(fakeDatabase);
    }

    @Test
    void shouldBeAbleToAddTransactionData() throws IllegalAmountException {
        // arrange
        long creditCardNumber = 12345678L;
        Transaction transaction = Transaction.createTransaction(new Date(), Categories.FOOD, 100.0);

        // act
        boolean result = transactionRepository.addTransactionData(creditCardNumber, transaction);

        // assert
        assertTrue(result);
        assertEquals(1, fakeDatabase.getTransactionData(creditCardNumber).size());
        assertEquals(transaction, fakeDatabase.getTransactionData(creditCardNumber).get(0));
    }

    @Test
    void shouldBeAbleToGetTransactionDataForCreditCardNumber() throws IllegalAmountException {
        // arrange
        long creditCardNumber = 12345678L;
        Transaction transaction = Transaction.createTransaction(new Date(), Categories.FOOD, 100.0);
        fakeDatabase.insertIntoTransactionTable(creditCardNumber, transaction);

        // act
        List<Transaction> transactions = transactionRepository.getTransactionDataFor(creditCardNumber);

        // assert
        assertEquals(Collections.singletonList(transaction), transactions);
    }

    @Test
    void shouldBeAbleToReturnEmptyListWhenNoTransactionsForCreditCardNumber() {
        // arrange
        long creditCardNumber = 12345678L;

        // act
        List<Transaction> transactions = transactionRepository.getTransactionDataFor(creditCardNumber);

        // assert
        assertTrue(transactions.isEmpty());
    }
}
