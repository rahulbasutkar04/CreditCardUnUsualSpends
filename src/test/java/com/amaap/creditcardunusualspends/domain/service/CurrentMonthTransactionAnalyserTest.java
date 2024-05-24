package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.util.TransactionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrentMonthTransactionAnalyserTest {
    TransactionBuilder transactionBuilder=new TransactionBuilder();

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

}