package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    @Test
    void shouldBeAbleToRespondWithOkIfTransactionHasInitialised()
    {
        // arrange
        TransactionController transactionController=new TransactionController();
        Response expected=new Response(Http.OK,"Transaction Initialised.");

        // act
        Response actual=transactionController.initialiseTransaction();

        // assert
        assertEquals(expected,actual);
    }
}
