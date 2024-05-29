package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {

    @Test
    void shouldBeAbleToRespondWithOKIfCustomerISCreated()
    {
        // arrange
        CustomerController customerController=new CustomerController();
        String name="Rahul Basutkar";
        String email="rahulbasutkar33@gmail.com";

        Response expected=new Response(Http.OK,"Customer Created");
        // act
        Response actual = customerController.create(name,email);

        // assert
        assertEquals(expected,actual);
    }
}
