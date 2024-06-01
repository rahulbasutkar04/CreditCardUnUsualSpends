package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.domain.model.valueobject.SpendCategory;
import com.amaap.creditcardunusualspends.util.DateBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzerTest {

    @Test
    void shouldBeAbleToGetTheUnusualSpendList() {
        // arrange
        Analyzer analyzer = new Analyzer();
        List<Transaction> currentMonthTransaction = new ArrayList<>();
        List<Transaction> previousMonthTransaction = new ArrayList<>();
        DateBuilder dateBuilder = new DateBuilder();
        CreditCard creditCard = new CreditCard(1);

        currentMonthTransaction.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 05, 20), SpendCategory.GROCERIES, 1000));
        previousMonthTransaction.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 04, 10), SpendCategory.GROCERIES, 200));
        previousMonthTransaction.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 04, 12), SpendCategory.GROCERIES, 100));

        // act
        List<Map<String, Object>> spend = analyzer.findUnusualSpends(currentMonthTransaction, previousMonthTransaction, 60);

        // assert
        assertEquals(1, spend.size());

    }
}