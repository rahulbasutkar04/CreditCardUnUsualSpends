package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.ExpenditureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenditureControllerTest {
    ExpenditureService expenditureService;

    @BeforeEach
    void setUp()
    {
        expenditureService=new ExpenditureService();
    }
    @Test
    void shouldBeAbleToRespondWithOkIfTransactionsArePresentToGetTheUnusualSpends()
    {
        // arrange
        ExpenditureController expenditureController=new ExpenditureController(expenditureService);
        Response expected=new Response(Http.OK,"Transactions are present..");

        // act
        Response actual = expenditureController.getSpends();

        // assert
        assertEquals(expected,actual);
    }
}
