package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.Customer;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.google.inject.Inject;

import java.util.List;

public class InMemoryCustomerRepository implements CustomerRepository {
    private Database database;

    @Inject
    public InMemoryCustomerRepository(Database database) {
        this.database = database;
    }

    @Override
    public void addCustomerData(Customer customer) {
        database.insertIntoCustomerTable(customer);
    }

    @Override
    public List<Customer> getCustomer() {
        return database.getCustomerList();
    }
}
