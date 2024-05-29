package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.Customer;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCustomerNameException;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;
import com.google.inject.Inject;

public class CustomerService {

    UserValidator userValidator = new UserValidator();

    private final CustomerRepository customerRepository;

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


        Customer customer=new Customer(name,email);
           customerRepository.addCustomerData(customer);

        return customerRepository.getCustomer().size() != 0;
    }

}
