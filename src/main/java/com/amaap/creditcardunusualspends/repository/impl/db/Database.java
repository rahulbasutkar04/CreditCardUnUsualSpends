package com.amaap.creditcardunusualspends.repository.impl.db;

import com.amaap.creditcardunusualspends.domain.model.entity.Customer;

import java.util.List;

public interface Database {

    void insertIntoCustomerTable(Customer customer);

    List<Customer> getCustomerList();
}
