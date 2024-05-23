package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    TransactionService transactionService;
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    void shouldReturnOkWhenTransactionIsSuccessful() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 200.0;

        when(transactionService.performTransaction(date, category, amount)).thenReturn(true);

        // act
        Response response = transactionController.initialiseTransaction(date, category, amount);

        // assert
        assertEquals(new Response(Http.OK, "Transaction Initialised."), response);
    }

    @Test
    void shouldReturnBadRequestWhenTransactionFails() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = 200.0;

        when(transactionService.performTransaction(date, category, amount)).thenReturn(false);

        // act
        Response response = transactionController.initialiseTransaction(date, category, amount);

        // assert
        assertEquals(new Response(Http.BAD_REQUEST, "Transaction Not Initialised"), response);

    }

    @Test
    void shouldThrowIllegalAmountExceptionWhenTransactionAmountIsNegative() throws IllegalAmountException {
        // arrange
        Date date = new Date();
        Categories category = Categories.FOOD;
        double amount = -200.0;

        doThrow(new IllegalAmountException("Amount cannot be negative")).when(transactionService).performTransaction(date, category, amount);

        // act & assert
        IllegalAmountException exception = assertThrows(IllegalAmountException.class, () -> {
            transactionController.initialiseTransaction(date, category, amount);
        });
        assertEquals("Amount cannot be negative", exception.getMessage());
    }
}
