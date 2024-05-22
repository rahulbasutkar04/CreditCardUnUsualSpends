package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.TransactionService;

public class TransactionController {
    private  final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response performTransaction() {

        if(transactionService.perFromTransaction()){
            return  new Response(Http.OK,"Transaction Initialized..");

        }
        else
        {
            return  new Response(Http.BAD_REQUEST,"Transaction Not Initialized..");

        }

    }
}
