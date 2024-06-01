package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCustomerNameException;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;
import com.google.inject.Inject;

public class CustomerService {

    UserValidator userValidator = new UserValidator();

    private final CustomerRepository customerRepository;
    private Customer lastAddedCustomer;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean create(String name, String email) throws CustomerException {

        if (!userValidator.isValidName(name)) {
            throw new InvalidCustomerNameException("Invalid Name: " + name);
        }

        if (!userValidator.isValidEmail(email)) {
            throw new InvalidEmailException("Invalid Email: " + email);
        }
        Customer customer = new Customer(name, email);

        customerRepository.addCustomerData(customer);

        lastAddedCustomer = customerRepository.findCustomerByNameAndEmail(name, email);

        return customerRepository.getCustomer().size() != 0;
    }

    public Customer getLastAddedCustomer() {
        return lastAddedCustomer;
    }
}
