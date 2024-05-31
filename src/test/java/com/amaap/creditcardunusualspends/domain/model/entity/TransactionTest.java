package com.amaap.creditcardunusualspends.domain.model.entity;

import com.amaap.creditcardunusualspends.domain.model.valueobject.SpendCategory;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransaction() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;

        // act
        Transaction transaction = new Transaction(creditCardNumber, date, category, amount);

        // assert
        assertEquals(creditCardNumber, transaction.getCreditCardNumber());
        assertEquals(date, transaction.getDate());
        assertEquals(category, transaction.getCategory());
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    void shouldBeAbleToCheckEquality() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date, category, amount);
        Transaction transaction2 = new Transaction(creditCardNumber, date, category, amount);

        // assert
        assertEquals(transaction1, transaction2);
    }

    @Test
    void shouldBeAbleToCheckInequalityWithDifferentCreditCardNumber() {
        // arrange
        long creditCardNumber1 = 1234567890123456L;
        long creditCardNumber2 = 1234567890123457L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber1, date, category, amount);
        Transaction transaction2 = new Transaction(creditCardNumber2, date, category, amount);

        // assert
        assertNotEquals(transaction1, transaction2);
    }

    @Test
    void shouldBeAbleToCheckInequalityWithDifferentDate() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis() + 1000); // different date
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date1, category, amount);
        Transaction transaction2 = new Transaction(creditCardNumber, date2, category, amount);

        // assert
        assertNotEquals(transaction1, transaction2);
    }

    @Test
    void shouldBeAbleToCheckInequalityWithDifferentCategory() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category1 = SpendCategory.GROCERIES;
        SpendCategory category2 = SpendCategory.TRAVEL;
        long amount = 1000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date, category1, amount);
        Transaction transaction2 = new Transaction(creditCardNumber, date, category2, amount);

        // assert
        assertNotEquals(transaction1, transaction2);
    }

    @Test
    void shouldBeAbleToCheckInequalityWithDifferentAmount() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount1 = 1000;
        long amount2 = 2000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date, category, amount1);
        Transaction transaction2 = new Transaction(creditCardNumber, date, category, amount2);

        // assert
        assertNotEquals(transaction1, transaction2);
    }

    @Test
    void shouldBeAbleToGenerateCorrectHashCode() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date, category, amount);
        Transaction transaction2 = new Transaction(creditCardNumber, date, category, amount);

        // assert
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    void shouldBeAbleToGenerateDifferentHashCodeForDifferentTransactions() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis() + 1000);
        SpendCategory category1 = SpendCategory.GROCERIES;
        SpendCategory category2 = SpendCategory.TRAVEL;
        long amount1 = 1000;
        long amount2 = 2000;

        // act
        Transaction transaction1 = new Transaction(creditCardNumber, date1, category1, amount1);
        Transaction transaction2 = new Transaction(creditCardNumber, date2, category2, amount2);

        // assert
        assertNotEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    void shouldBeAbleToConvertToString() {
        // arrange
        long creditCardNumber = 1234567890123456L;
        Date date = new Date();
        SpendCategory category = SpendCategory.GROCERIES;
        long amount = 1000;
        String expectedString = "Transaction{" +
                "creditCardNumber=" + creditCardNumber +
                ", date=" + date +
                ", category=" + category +
                ", amount=" + amount +
                '}';

        // act
        Transaction transaction = new Transaction(creditCardNumber, date, category, amount);

        // assert
        assertEquals(expectedString, transaction.toString());
    }
}
