package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.Customer;

import java.util.List;

public interface CustomerRepository {

    void addCustomerData(Customer customer);

    List<Customer> getCustomer();
}
