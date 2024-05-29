package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditControllerTest {


    Injector injector= Guice.createInjector(new CreditCardModule());
    CreditCardService creditCardService=injector.getInstance(CreditCardService.class);
    @Test
    void shouldBeAbleToRespondWithOkIfCreditCardIsAssignedToTheGivenUserId() {
        // arrange
        CreditCardController creditCardController = new CreditCardController(creditCardService);
        int userId = 1;
        Response expected = new Response(Http.OK, "Credit Card Assigned");

        // act
        Response actual = creditCardController.createCardFor(userId);

        // assert

        assertEquals(expected, actual);
    }
}
