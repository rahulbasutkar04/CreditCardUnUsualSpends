package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCustomerIdException;
import com.amaap.creditcardunusualspends.service.exception.NoUserIdFoundException;
import com.google.inject.Inject;

import java.util.List;

public class CreditCardService {
    private CustomerRepository customerRepository;
    private CreditCardRepository creditCardRepository;
    long creditCardNumber = 0;

    @Inject
    public CreditCardService(CustomerRepository customerRepository, CreditCardRepository creditCardRepository) {
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
    }

    public boolean createCard(int userId) throws CustomerException {

        if (userId <= 0) {
            throw new InvalidCustomerIdException("Invalid User Id Format");
        }
        List<Customer> customers = customerRepository.getCustomer();
        if (!customers.stream().anyMatch(customer -> customer.getId() == userId)) {
            throw new NoUserIdFoundException("User id not found.");
        }

        CreditCard creditCard = new CreditCard(userId);
        creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        return creditCardRepository.getCreditCards().size() != 0;

    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }
}
