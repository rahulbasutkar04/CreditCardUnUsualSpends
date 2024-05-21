package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.UserService;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Response createUser(int userId, String name, String email) throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException {
        if(userService.CreateUser(userId,name,email)) {
            return new Response(Http.OK, "User Created..");
        }

        return  new Response(Http.BAD_REQUEST,"User Not Created..");

    }
}
