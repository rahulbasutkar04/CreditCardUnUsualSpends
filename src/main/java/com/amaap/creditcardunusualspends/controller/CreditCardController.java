package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.google.inject.Inject;

public class CreditCardController {
    private CreditCardService creditCardService;

    @Inject
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response createCardFor(int userId) throws CustomerException {

        if (creditCardService.createCard(userId)) {
            return new Response(Http.OK, "Credit Card Assigned");
        }

        return new Response(Http.BAD_REQUEST, "Unable To Assign Credit Card");

    }
}
