package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;

public class UserController {
    public Response createUser(int userId, String name, String email) {
        return new Response(Http.Ok,"User Created..");
    }
}
