package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.controller.UserController;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionServiceTest {
    private TransactionService transactionService;
    private CreditCardRepository creditCardRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        userRepository = injector.getInstance(UserRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionRepository = injector.getInstance(TransactionRepository.class);
        transactionService = new TransactionService(creditCardRepository, transactionRepository);
        userService = new UserService(userRepository);

    }

    @Test
    void shouldBeAbleToReturnTrueIfTransactionIsStartedForTheCreditCardNumber() throws IllegalAmountException, InvalidCreditCardNumber, InvalidCreditCardNumberLength, InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException, DuplicateUserIdException {
        // arrange
        UserController userController = new UserController(userService);
        userController.createUser(1, "Rahul", "rahulbasutkar33@gmail.com");
        CreditCardService creditCardService = new CreditCardService(userRepository, creditCardRepository);
        creditCardService.CreateCard(12345678);
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 200.0;

        // act
        boolean result = transactionService.performTransaction(date, category, amount);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToCheckWhetherTransactionIsSavedInMemoryOrNot() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException, InvalidCreditCardNumber, InvalidCreditCardNumberLength, IllegalAmountException, DuplicateUserIdException {
        // arrange
        UserController userController = new UserController(userService);
        userController.createUser(1, "Rahul", "rahulbasutkar33@gmail.com");
        CreditCardService creditCardService = new CreditCardService(userRepository, creditCardRepository);
        creditCardService.CreateCard(12345678);
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 200.0;

        // act
        transactionService.performTransaction(date, category, amount);
        transactionService.performTransaction(new Date(24, 04, 22), Categories.GROCERY, 400.0);

        int insertedTransactionSize=transactionRepository.getTransactionDataFor(12345678L).size();

        // assert

        assertEquals(2,insertedTransactionSize);
    }


}
