package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.UserService;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Inject;

public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Response createUser(int userId, String name, String email) throws CreditCardException {
        if (userService.createUser(userId, name, email)) {
            return new Response(Http.OK, "User Created..");
        }
        return new Response(Http.BAD_REQUEST, "User Not Created..");
    }
}
