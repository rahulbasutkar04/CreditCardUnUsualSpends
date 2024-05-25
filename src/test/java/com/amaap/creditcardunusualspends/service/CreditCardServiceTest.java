package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.controller.UserController;
import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardServiceTest {
    private CreditCardService creditCardService;
    private UserController userController;
    private CreditCardRepository creditCardRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        userRepository = injector.getInstance(UserRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        creditCardService = new CreditCardService(userRepository, creditCardRepository);
        UserService userService = new UserService(userRepository);
        userController = new UserController(userService);

    }

    @Test
    void shouldBeABleToReturnTrueIfCreditCardIsSavedWithTheUserId() throws CreditCardException {
        // arrange
        long creditCardNumber = 12345678;

        // act
        boolean isCreditCardSaved = creditCardService.createCard(creditCardNumber);

        // assert
        assertTrue(isCreditCardSaved);
    }

    @Test
    void shouldThrowExceptionIfCreditCardNumberIsInvalid() {
        // arrange
        long creditCardNumber = 0;

        // act & assert
        assertThrows(InvalidCreditCardNumber.class, () -> {
            creditCardService.createCard(creditCardNumber);
        });
    }

    @Test
    void shouldThrowExceptionIfCreditCardNumberLengthIsInvalid() {
        // arrange
        long creditCardNumber = 123456;

        // act & assert
        assertThrows(InvalidCreditCardNumberLength.class, () -> {
            creditCardService.createCard(creditCardNumber);
        });
    }

    @Test
    void shouldBeAbleToGetTheStoredUserIdAndCreditCardNumberFromRepository() throws CreditCardException {
        // arrange
        userController.createUser(1, "Rahul Basutkar", "rahulbasutkar33@gmial.com");
        long creditCardNumber = 12345678;
        Map<Integer, Long> expectedData = new HashMap<>();
        expectedData.put(1, creditCardNumber);

        // act
        creditCardService.createCard(creditCardNumber);

        // assert
        assertEquals(expectedData, creditCardRepository.getCreditCardDetails());
    }

    @Test
    void shouldThrowExceptionIfDuplicateCreditCardNumberIsAdded() throws CreditCardException {
        // arrange
        userController.createUser(1, "Rahul Basutkar", "rahulbasutkar33@gmail.com");
        long creditCardNumber = 12345678;

        // act
        creditCardService.createCard(creditCardNumber);

        // assert
        assertThrows(DuplicateCreditCardException.class, () -> {
            creditCardService.createCard(creditCardNumber);
        });
    }

}