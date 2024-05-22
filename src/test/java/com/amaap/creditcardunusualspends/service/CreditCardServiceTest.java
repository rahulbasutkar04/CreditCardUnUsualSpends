package com.amaap.creditcardunusualspends.service;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardServiceTest {
    UserRepository userRepository;
    @BeforeEach
    void setUp()
    {
        Injector injector= Guice.createInjector(new UserModule());
        userRepository=injector.getInstance(UserRepository.class);
    }

    @Test
    void shouldBeABleToReturnTrueIfCreditCardIsSavedWithTheUserId() throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange
        CreditCardService creditCardService=new CreditCardService(userRepository);
        long creditCardNumber=12345678;

        // act
        boolean isCreditCardSaved= creditCardService.CreteCard(creditCardNumber);

        // assert
        assertTrue(isCreditCardSaved);
    }

    @Test
    void shouldThrowExceptionIfCreditCardNumberIsInvalid()
    {
        // arrange
        CreditCardService creditCardService=new CreditCardService(userRepository);
        long creditCardNumber=0;

        // act & assert
        assertThrows(InvalidCreditCardNumber.class,()->{
            creditCardService.CreteCard(creditCardNumber);
        });
    }
    @Test
    void shouldThrowExceptionIfCreditCardNumberLengthIsInvalid()
    {
        // arrange
        CreditCardService creditCardService=new CreditCardService(userRepository);
        long creditCardNumber=123456;

        // act & assert
        assertThrows(InvalidCreditCardNumberLength.class,()->{
            creditCardService.CreteCard(creditCardNumber);
        });
    }

}