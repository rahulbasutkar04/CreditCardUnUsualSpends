package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;
import com.google.inject.Inject;

public class CreditCardController {
    private final CreditCardService creditCardService;
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }
    public Response receiveCreditCardNumber(long creditCardNumber) {
        try {
            if (creditCardService.createCard(creditCardNumber)) {
                return new Response(Http.OK, "Credit Card Created..");
            }
        } catch (Exception e) {
            return new Response(Http.BAD_REQUEST, "Credit Card Not Created..");
        }
        return new Response(Http.BAD_REQUEST, "Unexpected Error..");
    }


}
