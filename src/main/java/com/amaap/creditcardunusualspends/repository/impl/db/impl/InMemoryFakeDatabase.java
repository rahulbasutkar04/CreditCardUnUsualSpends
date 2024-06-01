package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryFakeDatabase implements Database {

    List<Customer> customerList = new ArrayList<>();
    List<CreditCard> creditCardList = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();
    List<Map<String, Object>> spendDataList = new ArrayList<>();

    @Override
    public void insertIntoCustomerTable(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public void insertIntoCreditCardData(CreditCard creditCard) {
        creditCardList.add(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCardData() {
        return creditCardList;
    }

    @Override
    public void insertIntoTransactionTable(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionList;
    }

    @Override
    public boolean isCreditCardPresent(Long creditCardNumber) {
        return creditCardList.stream()
                .anyMatch(card -> card.getCreditCardNumber() == creditCardNumber);
    }

    @Override
    public void insertIntoExpenditureTable(List<Map<String, Object>> spendData) {
        spendDataList.addAll(spendData);
    }

    @Override
    public List<Map<String, Object>> getSpendsData() {
        return spendDataList;
    }

    @Override
    public void clear() {
        creditCardList.clear();
        customerList.clear();
        transactionList.clear();
        spendDataList.clear();
    }


}
