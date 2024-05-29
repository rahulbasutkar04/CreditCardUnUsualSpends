package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditControllerTest {
    @Test
    void shouldBeAbleToRespondWithOkIfCreditCardIsAssignedToTheGivenUserId() {
        // arrange
        CreditCardController creditCardController = new CreditController();
        int userId = 1;
        Response expected = new Response(Http.OK, "Credit Card Assigned");

        // act
        Response actual = creditCardController.createCardFor(userId);

        // assert

        assertEquals(expected, actual);
    }
}
