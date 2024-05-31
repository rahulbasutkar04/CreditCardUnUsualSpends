package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.util.DateBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
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

        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 5, 15), null, 100)); // May 15, 2024
        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 5, 20), null, 200)); // May 20, 2024
        transactionData.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 4, 25), null, 300)); // April 25, 2024

        // act
        List<Transaction> currentMonthTransactions = dataExtractor.getCurrentMonthTransactions(transactionData);

        // assert
        assertEquals(2, currentMonthTransactions.size());
        assertEquals(dateBuilder.createDate(2024, 5, 15), currentMonthTransactions.get(0).getDate());
        assertEquals(dateBuilder.createDate(2024, 5, 20), currentMonthTransactions.get(1).getDate());
    }
}
