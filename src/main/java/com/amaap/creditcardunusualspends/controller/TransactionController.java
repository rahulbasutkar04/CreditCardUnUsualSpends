package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;

public class TransactionController {
    public Response initialiseTransaction(long creditCardNumber) {
        return new Response(Http.OK,"Transaction Initialised");
    }
}
