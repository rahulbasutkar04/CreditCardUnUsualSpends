package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.CustomerService;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.google.inject.Inject;

public class CustomerController {
    CustomerService customerService;
    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    public Response create(String name, String email) throws CustomerException {
        if(customerService.create(name,email)) return new Response(Http.OK,"Customer Created");
        else return new Response(Http.BAD_REQUEST,"Customer Not Created");
    }
}
