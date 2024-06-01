package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.ExpenditureService;
import com.google.inject.Inject;

public class ExpenditureController {
    private final ExpenditureService expenditureService;


    @Inject
    public ExpenditureController(ExpenditureService expenditureService) {
        this.expenditureService = expenditureService;
    }

    public Response getUnusualSpendsFor(long creditCardNumber) {

        try {
            expenditureService.getUnusualSpend(creditCardNumber);
            return new Response(Http.OK, "Unusual Spend Found");

        } catch (Exception e) {
            return new Response(Http.BAD_REQUEST, "No Unusual Spend Found");
        }
    }
}
