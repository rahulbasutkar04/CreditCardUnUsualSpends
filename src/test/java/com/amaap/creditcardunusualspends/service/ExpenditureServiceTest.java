package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpenditureServiceTest {

    TransactionRepository transactionRepository;
    CreditCardRepository creditCardRepository;
    ExpenditureService expenditureService;
    ExpenditureRepository expenditureRepository;
    TransactionService transactionService;
    CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        transactionRepository = injector.getInstance(TransactionRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        customerRepository = injector.getInstance(CustomerRepository.class);
        transactionService = new TransactionService(transactionRepository, creditCardRepository);
        expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, customerRepository);


    }

    @Test
    void shouldBeAbleToReturnTrueIfUnUsualSpendFound() throws InvalidDateException, InvaliCreditCardNumberException, InvalidCategoryException, CustomerException, InvalidAmountException {
        // arrange
        CustomerService customerService = new CustomerService(customerRepository);
        customerService.create("Rahul", "rahulbasutkar33@gmail.com");
        int userId = customerService.getLastAddedCustomer().getId();
        CreditCardService creditCardService = new CreditCardService(customerRepository, creditCardRepository);
        creditCardService.createCard(userId);
        long creditCardNumber = creditCardService.getCreditCardNumber();
        TransactionService transactionService = new TransactionService(transactionRepository, creditCardRepository);
        transactionService.performTransaction(creditCardNumber, "24-05-2024", "Travel", 100);
        transactionService.performTransaction(creditCardNumber, "01-06-2024", "Travel", 500);
        ExpenditureService expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, customerRepository);

        // act & assert
        assertTrue(expenditureService.getUnusualSpend(creditCardNumber));


    }

    @Test
    void shouldBeAbleToReturnFalseIfNoUnusualSpendFound() throws InvalidDateException, InvaliCreditCardNumberException, InvalidCategoryException, InvalidAmountException {
        // arrange
        CreditCard creditCard = new CreditCard(1);
        creditCardRepository.addCreditCardData(creditCard);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "20-05-2024", "Travel", 100);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "01-06-2024", "Travel", 100);

        // act
        boolean isSpendDetected = expenditureService.getUnusualSpend(creditCard.getCreditCardNumber());

        // assert
        assertFalse(isSpendDetected);

    }

}