package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.util.TransactionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LastMonthTransactionAnalyserTest {

    TransactionBuilder transactionBuilder=new TransactionBuilder();
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

}