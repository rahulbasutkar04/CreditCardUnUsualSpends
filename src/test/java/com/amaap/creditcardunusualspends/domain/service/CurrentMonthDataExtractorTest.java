package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.util.DateBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentMonthDataExtractorTest {

    @Test
    void ShouldBeAbleToSeparateAndGetCurrentMonthTransactions() {
        // arrange
        CurrentMonthDataExtractor dataExtractor = new CurrentMonthDataExtractor();
        DateBuilder dateBuilder = new DateBuilder();
        List<Transaction> transactionData = new ArrayList<>();
        CreditCard creditCard = new CreditCard(1);

        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 6, 15), null, 100)); // May 15, 2024
        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 6, 20), null, 200)); // May 20, 2024
        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 5, 25), null, 300)); // April 25, 2024

        // act
        List<Transaction> currentMonthTransactions = dataExtractor.getCurrentMonthTransactions(transactionData);

        // assert
        assertEquals(2, currentMonthTransactions.size());

    }
}
