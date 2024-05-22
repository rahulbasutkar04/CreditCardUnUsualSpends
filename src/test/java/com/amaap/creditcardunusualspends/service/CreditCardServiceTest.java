package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.controller.CreditCardController;
import com.amaap.creditcardunusualspends.controller.UserController;
import com.amaap.creditcardunusualspends.module.UserModule;
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
    UserRepository userRepository;
    CreditCardRepository creditCardRepository;
    CreditCardService creditCardService;
    CreditCardController creditCardController;
    UserController userController;
    UserService userService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());

        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        userRepository = injector.getInstance(UserRepository.class);
        creditCardService = new CreditCardService(userRepository, creditCardRepository);
        creditCardController = new CreditCardController(creditCardService);
        userService = injector.getInstance(UserService.class);
        userController = new UserController(userService);

    }

    @Test
    void shouldBeABleToReturnTrueIfCreditCardIsSavedWithTheUserId() throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange

        long creditCardNumber = 12345678;

        // act
        boolean isCreditCardSaved = creditCardService.CreateCard(creditCardNumber);

        // assert
        assertTrue(isCreditCardSaved);
    }

    @Test
    void shouldThrowExceptionIfCreditCardNumberIsInvalid() {
        // arrange
        long creditCardNumber = 0;

        // act & assert
        assertThrows(InvalidCreditCardNumber.class, () -> {
            creditCardService.CreateCard(creditCardNumber);
        });
    }

    @Test
    void shouldThrowExceptionIfCreditCardNumberLengthIsInvalid() {
        // arrange
        long creditCardNumber = 123456;

        // act & assert
        assertThrows(InvalidCreditCardNumberLength.class, () -> {
            creditCardService.CreateCard(creditCardNumber);
        });
    }

    @Test
    void shouldBeAbleToGetTheStoredUserIdAndCreditCardNumberFromRepository() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException, InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange
        userController.createUser(1, "Rahul Basutkar", "rahulbasutkar33@gmial.com");
        long creditCardNumber = 12345678;
        Map<Integer, Long> expectedData = new HashMap<>();
        expectedData.put(1, creditCardNumber);

        // act
        creditCardService.CreateCard(creditCardNumber);

        // assert
        assertEquals(expectedData, creditCardRepository.getCreditCardDetails());
    }

}