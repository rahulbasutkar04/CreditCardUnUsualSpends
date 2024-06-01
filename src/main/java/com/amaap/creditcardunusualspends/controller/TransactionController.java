package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.TransactionService;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Inject;

public class TransactionController {

    private TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response initialiseTransaction(long creditCardNumber, String date, String category, long amount) throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException {
        try {
            transactionService.performTransaction(creditCardNumber, date, category, amount);
            return new Response(Http.OK, "Transaction Initialised");

        } catch (Exception e) {
            return new Response(Http.BAD_REQUEST, "Transaction Not Initialised");
        }
    }
}
