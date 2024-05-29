package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;

public class CustomerController {
    public Response create(String name, String email) {

      return   new Response(Http.OK,"Customer Created");
    }
}
