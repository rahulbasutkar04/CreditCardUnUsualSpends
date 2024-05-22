package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class CreditCardControllerTest {

    CreditCardService creditCardService;
    UserRepository userRepository;
    CreditCardRepository creditCardRepository;
    @BeforeEach
    void setUp()
    {
        Injector injector = Guice.createInjector(new UserModule());
       userRepository= injector.getInstance(UserRepository.class);
       creditCardRepository=injector.getInstance(CreditCardRepository.class);
        creditCardService =new CreditCardService(userRepository,creditCardRepository);
    }
    @Test
    void shouldBeAbleToReceiveCreditCardNumber() throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange
        CreditCardController creditCardController = new CreditCardController(creditCardService);
        long creditCardNumber = 1234567;
        Response expected = new Response(Http.OK, "Credit Card Created..");

        //act
        Response actual = creditCardController.receiveCreditCardNumber(creditCardNumber);

        // assert
        assertEquals(expected,actual);
    }
}
