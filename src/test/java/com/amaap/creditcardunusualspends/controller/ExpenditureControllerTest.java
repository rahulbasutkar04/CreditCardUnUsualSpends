package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenditureControllerTest {

    @Test
    void shouldBeAbleToRespondWithOkIfUnusualSpendForCreditCard(){
        // arrange
        ExpenditureController expenditureController=new ExpenditureController();
        Response expected=new Response(Http.OK,"Unusual Spend Found");
        long creditCardNumber=1234556;

        // act
        Response actual = expenditureController.getUnusualSpendsFor(creditCardNumber);

        // assert
        assertEquals(expected,actual);
    }
}
