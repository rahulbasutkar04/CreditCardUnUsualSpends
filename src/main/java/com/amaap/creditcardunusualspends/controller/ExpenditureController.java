package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.ExpenditureService;
import com.google.inject.Inject;

public class ExpenditureController {

    @Inject
    public ExpenditureController(ExpenditureService expenditureService) {
        this.expenditureService = expenditureService;
    }

    private ExpenditureService expenditureService;
    public Response getSpends() {

        if(expenditureService.getSpends()){

            return new Response(Http.OK, "Transactions are present..");
        }
        else
        {
            return new Response(Http.BAD_REQUEST,"No transactions present");
        }
    }
}
