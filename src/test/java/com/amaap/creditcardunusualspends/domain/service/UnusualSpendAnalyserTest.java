package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.Transaction;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.domain.service.impl.DefaultUnusualSpendDetector;
import com.amaap.creditcardunusualspends.util.TransactionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnusualSpendAnalyserTest {

    TransactionBuilder transactionBuilder = new TransactionBuilder();
    UnusualSpendDetector unusualSpendDetector = new DefaultUnusualSpendDetector(50);
    UnusualSpendAnalyser unusualSpendAnalyser = new UnusualSpendAnalyser(unusualSpendDetector);

    @Test
    public void shouldBeAbleToTestCurrentMonthTransactions() throws IllegalAmountException {
        // arrange
        List<Transaction> transactions = transactionBuilder.currentMonthTransactionData();

        // act
        List<Transaction> currentMonthTransactions = CurrentMonthTransactionAnalyser.getCurrentMonthTransactions(transactions);

        // assert
        assertEquals(2, currentMonthTransactions.size());
        assertTrue(currentMonthTransactions.stream().anyMatch(t -> t.getCategories() == Categories.FOOD));
        assertTrue(currentMonthTransactions.stream().anyMatch(t -> t.getCategories() == Categories.GROCERY));

    }

    @Test
    public void shouldBeAbleToTestPreviousMonthTransactions() throws IllegalAmountException {
        // arrange
        List<Transaction> transactions = transactionBuilder.previousMonthTransactionData();

        // act
        List<Transaction> previousMonthTransactions = LastMonthTransactionAnalyser.getLastMonthTransactions(transactions);

        // assert
        assertEquals(2, previousMonthTransactions.size());
        assertTrue(previousMonthTransactions.stream().anyMatch(t -> t.getCategories() == Categories.GROCERY));
        assertTrue(previousMonthTransactions.stream().anyMatch(t -> t.getCategories() == Categories.ENTERTAINMENT));
    }

    @Test
    public void shouldBeAbleToTestCalculateUnusualSpends() throws IllegalAmountException {

        // arrange
        List<Transaction> transactions = transactionBuilder.allTransactionData();

        // act
        Map<Categories, Double> unusualSpends = unusualSpendAnalyser.calculateUnusualSpends(transactions);

        // assert
        assertEquals(2, unusualSpends.size());
        assertTrue(unusualSpends.containsKey(Categories.FOOD));
        assertEquals(300.0, unusualSpends.get(Categories.FOOD));
        assertTrue(unusualSpends.containsKey(Categories.GROCERY));
        assertEquals(450.0, unusualSpends.get(Categories.GROCERY));
    }
}