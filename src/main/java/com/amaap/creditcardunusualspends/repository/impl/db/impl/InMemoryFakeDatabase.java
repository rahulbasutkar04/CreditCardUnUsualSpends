package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;

import java.util.*;

public class InMemoryFakeDatabase implements FakeDatabase {
    private final Map<Integer, Map<String, String>> userData = new HashMap<>();
    private final Map<Integer, Long> creditCardData = new HashMap<>();
    private final Map<Long, List<Transaction>> transactionData = new HashMap<>();
    private final Map<Categories,Double> expenditureData=new HashMap<>();
    private int lastInsertedId = -1;

    @Override
    public void insertIntoUserTable(int id, String name, String email) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userData.put(id, userDetails);
        lastInsertedId = id;
    }

    @Override
    public Map<Integer, Map<String, String>> getUserData() {
        return userData;
    }

    @Override
    public int getId() {
        return lastInsertedId;
    }

    @Override
    public void InsertIntoCreditCardTable(int id, long creditCardNumber) {
        creditCardData.put(id, creditCardNumber);
    }

    @Override
    public Map<Integer, Long> getCreditCardData() {
        return creditCardData;
    }

    @Override
    public long getCreditCardNumber() {
        if (lastInsertedId == -1) {
            throw new IllegalStateException("No user has been inserted yet.");
        }
        Long creditCardNumber = creditCardData.get(lastInsertedId);
        if (creditCardNumber == null) {
            throw new IllegalArgumentException("No credit card number found for the last inserted user ID: " + lastInsertedId);
        }
        return creditCardNumber;
    }

    @Override
    public boolean insertIntoTransactionTable(long creditCardNumber, Transaction transaction) {
        transactionData.computeIfAbsent(creditCardNumber, k -> new ArrayList<>()).add(transaction);
        return true;
    }

    @Override
    public List<Transaction> getTransactionData(Long creditCardNumber) {
        return transactionData.getOrDefault(creditCardNumber, Collections.emptyList());
    }

    @Override
    public void insertIntoExpenditureDataTable(Map<Categories, Double> unusualSpendData) {
        expenditureData.putAll(unusualSpendData);
    }
}
