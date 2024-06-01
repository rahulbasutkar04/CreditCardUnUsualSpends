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

class UnusualSpendAnalyserTest {


    @Test
    void shouldBeAbleToDetectUnusualSpendAndReturn() {
        // arrange
        UnusualSpendAnalyser unusualSpendAnalyser = new UnusualSpendAnalyser();
        DateBuilder dateBuilder = new DateBuilder();
        List<Transaction> dummyTransactions = new ArrayList<>();
        CreditCard creditCard = new CreditCard(1);

        dummyTransactions.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 5, 24), SpendCategory.GROCERIES, 300));
        dummyTransactions.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 6, 24), SpendCategory.GROCERIES, 800));
        dummyTransactions.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 5, 24), SpendCategory.TRAVEL, 100));
        dummyTransactions.add(new Transaction(creditCard.getCreditCardNumber(), dateBuilder.createDate(2024, 6, 24), SpendCategory.TRAVEL, 800));

        // act
        List<Map<String, Object>> spendData = unusualSpendAnalyser.getSpends(dummyTransactions);

        // assert
        assertEquals(2, spendData.size());

    }


}