package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.CustomerService;
import com.google.inject.Inject;

public class CustomerController {
    CustomerService customerService;
    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    public Response create(String name, String email)  {
        try {
            customerService.create(name, email) ;
                return new Response(Http.OK, "Customer Created");
        } catch (Exception e) {
            return new Response(Http.BAD_REQUEST, "Customer Not Created");
        }
    }

    public int  getUserId() {
      return  customerService.getLastAddedCustomer().getId();
    }
}
