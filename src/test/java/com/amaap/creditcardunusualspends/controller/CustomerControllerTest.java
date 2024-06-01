package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {

    Injector injector = Guice.createInjector(new CreditCardModule());
    CustomerController customerController = injector.getInstance(CustomerController.class);

    @Test
    void shouldBeAbleToRespondWithOKIfCustomerISCreated() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";

        Response expected = new Response(Http.OK, "Customer Created");
        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToRespondWithBADREQUESTIfCustomerNotCreated() {
        // arrange
        String name = "";
        String email = "rahulbasutkar33@gmail.com";

        Response expected = new Response(Http.BAD_REQUEST, "Customer Not Created");
        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

}
