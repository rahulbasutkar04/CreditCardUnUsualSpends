package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.controller.UserController;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.CreditCardException;
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
        Injector injector = Guice.createInjector(new AppModule());
        userRepository = injector.getInstance(UserRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionRepository = injector.getInstance(TransactionRepository.class);
        transactionService = new TransactionService(creditCardRepository, transactionRepository);
        userService = new UserService(userRepository);

    }

    @Test
    void shouldBeAbleToReturnTrueIfTransactionIsStartedForTheCreditCardNumber() throws IllegalAmountException, CreditCardException {
        // arrange
        UserController userController = new UserController(userService);
        userController.createUser(1, "Rahul", "rahulbasutkar33@gmail.com");
        CreditCardService creditCardService = new CreditCardService(userRepository, creditCardRepository);
        creditCardService.createCard(12345678);
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 200.0;

        // act
        boolean result = transactionService.performTransaction(date, category, amount);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToCheckWhetherTransactionIsSavedInMemoryOrNot() throws CreditCardException, IllegalAmountException {
        // arrange
        UserController userController = new UserController(userService);
        userController.createUser(1, "Rahul", "rahulbasutkar33@gmail.com");
        CreditCardService creditCardService = new CreditCardService(userRepository, creditCardRepository);
        creditCardService.createCard(12345678);
        Date date = new Date(2024, 03, 10);
        Categories category = Categories.FOOD;
        double amount = 200.0;

        // act
        transactionService.performTransaction(date, category, amount);
        transactionService.performTransaction(new Date(2024, 04, 22), Categories.GROCERY, 400.0);

        int insertedTransactionSize = transactionRepository.getTransactionDataFor(12345678L).size();

        System.out.println(transactionRepository.getTransactionDataFor(12345678L));

        // assert
        assertEquals(2, insertedTransactionSize);
    }


}
