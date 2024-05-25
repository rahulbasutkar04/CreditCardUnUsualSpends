package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.Transaction;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransactionWithValidData() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 100.0;

        // act
        Transaction transaction = Transaction.createTransaction(date, category, amount);

        // assert
        assertNotNull(transaction);
        assertEquals(date, transaction.getDate());
        assertEquals(category, transaction.getCategories());
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = -100.0;

        // act & assert
        assertThrows(IllegalAmountException.class, () -> Transaction.createTransaction(date, category, amount));
    }

    @Test
    void shouldBeAbleToPerformTransactionsWithSameDataShouldBeEqual() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 100.0;

        Transaction transaction1 = Transaction.createTransaction(date, category, amount);
        Transaction transaction2 = Transaction.createTransaction(date, category, amount);

        // act & assert
        assertEquals(transaction1, transaction2);
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    void shouldBeAbleToPerformTransactionsWithDifferentDataShouldNotBeEqual() throws IllegalAmountException {
        // arrange
        Date date1 = new Date();
        Date date2 = new Date();
        Categories category1 = Categories.FOOD;
        Categories category2 = Categories.GROCERY;
        double amount1 = 100.0;
        double amount2 = 200.0;

        Transaction transaction1 = Transaction.createTransaction(date1, category1, amount1);
        Transaction transaction2 = Transaction.createTransaction(date2, category2, amount2);

        // act & assert
        assertNotEquals(transaction1, transaction2);
        assertNotEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    void ShouldBeAbleToCheckToStringAndReturnCorrectFormat() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 100.0;

        Transaction transaction = Transaction.createTransaction(date, category, amount);

        // act
        String expectedString = "Transaction{date=" + date + ", categories=" + category + ", amount=" + amount + "}";

        // assert
        assertEquals(expectedString, transaction.toString());
    }
}
