package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
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

    @Override
    public Customer findCustomerByNameAndEmail(String name, String email) {
        return database.getCustomerList().stream()
                .filter(customer -> customer.getName().equals(name) && customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer getCustomerById(int id) {
        return database.getCustomerList().stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
