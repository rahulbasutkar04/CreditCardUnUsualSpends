package com.amaap.creditcardunusualspends.repository.impl.db;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.List;

public interface Database {

    void insertIntoCustomerTable(Customer customer);

    List<Customer> getCustomerList();

    void insertIntoCreditCardData(CreditCard creditCard);

    List<CreditCard> getCreditCardData();

    void insertIntoTransactionTable(Transaction transaction);

    List<Transaction> getTransactions();

    boolean isCreditCardPresent(Long creditCardNumber);
}
