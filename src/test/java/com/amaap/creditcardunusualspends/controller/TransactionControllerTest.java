package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    Injector injector = Guice.createInjector(new CreditCardModule());
    CustomerController customerController = injector.getInstance(CustomerController.class);
    CreditCardController creditCardController = injector.getInstance(CreditCardController.class);
    TransactionController transactionController = injector.getInstance(TransactionController.class);

    @Test
    void shouldBeAbleToRespondWithOkIfTransactionIsInitialised() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException {
        // arrange
        customerController.create("Rahul Basutkar", "rahulbasutkar@gmail.com");
        creditCardController.createCardFor(customerController.getUserId());
        Response expected = new Response(Http.OK, "Transaction Initialised");

        // act
        Response actual = transactionController.initialiseTransaction(creditCardController.getCreditCard(), "24-05-2024", "Groceries", 500);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToRespondWithBADREQUESTIfTransactionIsInitialised() throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException {
        // arrange
        customerController.create("Rahul Basutkar", "rahulbasutkar@gmail.com");
        creditCardController.createCardFor(customerController.getUserId());
        Response expected = new Response(Http.BAD_REQUEST, "Transaction Not Initialised");

        // act
        Response actual = transactionController.initialiseTransaction(creditCardController.getCreditCard(), "24-05-2024", "Groceries", 0);

        // assert
        assertEquals(expected, actual);
    }


}
