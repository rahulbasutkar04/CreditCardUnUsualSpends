package com.amaap.creditcardunusualspends.repository.impl.db;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.Transaction;

import java.util.List;
import java.util.Map;

public interface FakeDatabase {
    void insertIntoUserTable(int id,String name,String email);
    Map<Integer, Map<String, String>> getUserData();
    int getId();

    void InsertIntoCreditCardTable(int id,long CreditCardNumber);

    Map<Integer, Long> getCreditCardData();

    long getCreditCardNumber();


    boolean insertIntoTransactionTable(long creditCardNumber, Transaction transaction);

    List<Transaction> getTransactionData(Long creditCardId);

    void insertIntoExpenditureDataTable(Map<Categories, Double> unusualSpendData);

    String getUserNameById(int userId);

    String getEmailById(int userId);

    long getCreditCardNumberById(int userId);

    Map<String, Double> getUnUsualSpendDataFor(long ccNumber);
}
