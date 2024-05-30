package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
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

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        transactionRepository = injector.getInstance(TransactionRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        transactionService = new TransactionService(transactionRepository, creditCardRepository);
         expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository);


    }


    @Test
    void shouldBeAbleToReturnTrueIfUnUsualSpendFound() throws InvalidDateException, InvaliCreditCardNumberException, InvalidCategoryException {

        // arrange
        CreditCard creditCard = new CreditCard(1);
        creditCardRepository.addCreditCardData(creditCard);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "24-04-2024", "Travel", 100);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "24-05-2024", "Travel", 500);

        // act
       boolean isSpendDetected= expenditureService.getUnusualSpend(creditCard.getCreditCardNumber());

       // assert
        assertTrue(isSpendDetected);

    }

    @Test
    void shouldBeAbleToReturnFalseIfNoUnusualSpendFound() throws InvalidDateException, InvaliCreditCardNumberException, InvalidCategoryException {
        // arrange
        CreditCard creditCard = new CreditCard(1);
        creditCardRepository.addCreditCardData(creditCard);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "24-04-2024", "Travel", 100);
        transactionService.performTransaction(creditCard.getCreditCardNumber(), "24-05-2024", "Travel", 100);

        // act
        boolean isSpendDetected= expenditureService.getUnusualSpend(creditCard.getCreditCardNumber());

        // assert
        assertFalse(isSpendDetected);

    }

}