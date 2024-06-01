package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidAmountException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        transactionRepository = injector.getInstance(TransactionRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionService = new TransactionService(transactionRepository, creditCardRepository);
    }


    @Test
    void shouldBeAbleToReturnTrueIfTransactionIsPerformedAndSaved() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException, InvalidAmountException {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        String date = "01-04-2024";
        String category = "Travel";
        long amount = 200;


        // act
        boolean isPerformed = transactionService.performTransaction(creditCardNumber, date, category, amount);

        // assert
        assertTrue(isPerformed);
    }

    @Test
    void shouldBeAbleToReturnFalseIfInvalidDateFormatIsGiven() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException, InvalidAmountException {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        String date = "01-042024";
        String category = "Travel";
        long amount = 200;

        // act
        boolean isPerformed = transactionService.performTransaction(creditCardNumber, date, category, amount);

        // assert
        assertFalse(isPerformed);
    }

    @Test
    void shouldBeAbleToReturnFalseIfInvalidCategoryIsGiven() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException, InvalidAmountException {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        String date = "01-04-2024";
        String category = "Food";
        long amount = 200;

        // act
        boolean isPerformed = transactionService.performTransaction(creditCardNumber, date, category, amount);

        // assert
        assertFalse(isPerformed);
    }

    @Test
    void shouldThrowExceptionIfDateIsEmpty() {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        String date = "";
        String category = "Travel";
        long amount = 200;

        // act & assert
        assertThrows(InvalidDateException.class, () -> {
            transactionService.performTransaction(creditCardNumber, date, category, amount);
        });

    }

    @Test
    void shouldThrowExceptionIfCategoryIsEmpty() {
        // arrange
        long creditCardNumber = 12345678;
        String date = "04-04-2024";
        String category = "";
        long amount = 200;

        // act & assert
        assertThrows(InvalidCategoryException.class, () -> {
            transactionService.performTransaction(creditCardNumber, date, category, amount);
        });

    }

    @Test
    void shouldThrowExceptionIfGivenTransactionAmountIsInvalid() {
        // arrange
        long creditCardNumber = 12345678;
        String date = "04-04-2024";
        String category = "Travel";
        long amount = 0;

        // act & assert
        assertThrows(InvalidAmountException.class, () -> {
            transactionService.performTransaction(creditCardNumber, date, category, amount);
        });


    }

    @Test
    void shouldThrowExceptionIfCreditCardIsNotFund() {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = 12345678;
        creditCardRepository.addCreditCardData(creditCard);
        String date = "01-04-2024";
        String category = "Travel";
        long amount = 200;

        // act & assert
        assertThrows(InvaliCreditCardNumberException.class, () -> {
            transactionService.performTransaction(creditCardNumber, date, category, amount);
        });

    }

    @Test
    void shouldReturnTrueIfTransactionDataIsPresent() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException, InvalidAmountException {
        // arrange
        CreditCard creditCard = new CreditCard(2);
        long creditCardNumber = creditCard.getCreditCardNumber();
        creditCardRepository.addCreditCardData(creditCard);
        String date = "01-04-2024";
        String category = "TRAVEL";
        long amount = 200;

        // act
        transactionService.performTransaction(creditCardNumber, date, category, amount);
        boolean isDataPresent = transactionRepository.getTransactionData().size() != 0;

        // assert
        assertTrue(isDataPresent);
    }


}