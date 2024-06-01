package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCustomerIdException;
import com.amaap.creditcardunusualspends.service.exception.NoUserIdFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditCardServiceTest {
    private CreditCardRepository creditCardRepository;
    private CustomerRepository customerRepository;
    private CreditCardService creditCardService;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        customerRepository = injector.getInstance(CustomerRepository.class);
        creditCardService = new CreditCardService(customerRepository, creditCardRepository);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void shouldBeAbleToReturnTrueOfCreditCardIsAssignedToTheUser() throws CustomerException {
        // arrange
        customerService.create("Rahul", "rahulbasutkat33@gmail.com");
        int customerId = customerService.getLastAddedCustomer().getId();

        // act & assert
        assertTrue(creditCardService.createCard(customerId));

    }


    @Test
    void shouldThrowExceptionIfUserIdGivenByUserIsNotPresentInRecords() throws CustomerException {
        // arrange
        customerService.create("Rahul", "rahulbasutkat33@gmail.com");

        // act & assert
        assertThrows(NoUserIdFoundException.class, () -> {
            creditCardService.createCard(10);
        });

    }

    @Test
    void shouldThrowExceptionIfUserIdGivenByUserIsInvalidFormat() throws CustomerException {
        // arrange
        customerService.create("Rahul", "rahulbasutkat33@gmail.com");

        // act & assert
        assertThrows(InvalidCustomerIdException.class, () -> {
            creditCardService.createCard(-1);
        });

    }

    @Test
    void shouldReturnTrueIfCreditCardIsCreatedAndSaved() throws CustomerException {
        // arrange
        customerService.create("Rahul", "rahulbasutkat33@gmail.com");
        int customerId = customerService.getLastAddedCustomer().getId();

        // act
        creditCardService.createCard(customerId);
        boolean isCreditCardCreated = creditCardRepository.getCreditCards().size() != 0;

        // assert
        assertTrue(isCreditCardCreated);
    }

    @Test
    void shouldReturnTrueIfCustomerIsCreatedAndSaved() throws CustomerException {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";

        // act
        boolean isCustomerCreated = customerService.create(name, email);

        // assert
        assertTrue(isCustomerCreated);
    }

}