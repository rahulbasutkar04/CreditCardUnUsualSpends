package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.service.TransactionService;

import java.util.Date;

public class TransactionController {
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response initialiseTransaction(Date date, Categories categories, Double amount) throws IllegalAmountException {
        if (transactionService.performTransaction(date, categories, amount))
            return new Response(Http.OK, "Transaction Initialised.");
        else {
            return new Response(Http.BAD_REQUEST, "Transaction Not Initialised");
        }
    }
}
