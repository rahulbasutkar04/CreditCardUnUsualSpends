package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.service.CustomerService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditControllerTest {


    Injector injector = Guice.createInjector(new CreditCardModule());
    CustomerRepository customerRepository = injector.getInstance(CustomerRepository.class);
    CustomerService customerService = new CustomerService(customerRepository);
    CustomerController customerController = new CustomerController(customerService);
    CreditCardController creditCardController = injector.getInstance(CreditCardController.class);

    @Test
    void shouldBeAbleToRespondWithOkIfCreditCardIsAssignedToTheGivenUserId() {
        // arrange
        customerController.create("Rahul", "rahulbasutkar@gmail.com");
        int userId = customerController.getUserId();
        Response expected = new Response(Http.OK, "Credit Card Assigned");

        // act
        Response actual = creditCardController.createCardFor(userId);

        // assert
        assertEquals(expected, actual);
    }


    @Test
    void shouldBeAbleToRespondWithBADREQUESTIfCreditCardIsNotAssigned() {
        // arrange
        int userId = 2;
        Response expected = new Response(Http.BAD_REQUEST, "Unable To Assign Credit Card");

        // act
        Response actual = creditCardController.createCardFor(userId);

        // assert
        assertEquals(expected, actual);
    }
}
