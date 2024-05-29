package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.domain.Customer;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFakeDatabase implements Database {

    List<Customer> customerList=new ArrayList<>();
    @Override
    public void insertIntoCustomerTable(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerList;
    }
}
