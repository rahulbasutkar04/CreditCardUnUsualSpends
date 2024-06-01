package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenditureControllerTest {

    private CustomerController customerController;
    private CreditCardController creditCardController;
    private TransactionController transactionController;
    private ExpenditureController expenditureController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        customerController = injector.getInstance(CustomerController.class);
        creditCardController = injector.getInstance(CreditCardController.class);
        transactionController = injector.getInstance(TransactionController.class);
        expenditureController = injector.getInstance(ExpenditureController.class);
    }

    @Test
    void shouldBeAbleToRespondWithOkIfUnusualSpendFoundForCreditCard() throws InvaliCreditCardNumberException, InvalidDateException, InvalidCategoryException {
        // arrange
        customerController.create("Rahul Basutkar", "rahul@gmail.com");
        int userId = customerController.getUserId();
        creditCardController.createCardFor(userId);
        long creditCardNumber = creditCardController.getCreditCard();
        transactionController.initialiseTransaction(creditCardNumber, "25-05-2024", "Travel", 100);
        transactionController.initialiseTransaction(creditCardNumber, "01-06-2024", "Travel", 500);

        Response expected = new Response(Http.OK, "Unusual Spend Found");

        // act
        Response actual = expenditureController.getUnusualSpendsFor(creditCardNumber);

        // assert
        assertEquals(expected, actual);
    }


    @Test
    void shouldBeAbleToRespondWithBADREQUESTIfUnusualSpendNotFoundForCreditCard() {
        // arrange
        Response expected = new Response(Http.BAD_REQUEST, "No Unusual Spend Found");
        long creditCardNumber = 1234556;

        // act
        Response actual = expenditureController.getUnusualSpendsFor(creditCardNumber);

        // assert
        assertEquals(expected, actual);
    }
}
