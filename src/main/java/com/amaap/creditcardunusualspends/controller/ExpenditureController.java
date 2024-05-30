package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;

public class ExpenditureController {
    public Response getUnusualSpendsFor(long creditCardNumber) {
        return new Response(Http.OK,"Unusual Spend Found");
    }
}
