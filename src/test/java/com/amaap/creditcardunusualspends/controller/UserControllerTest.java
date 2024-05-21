package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    @Test
    void shouldBeAbleToCreateCustomer()
    {
        // arrange
        int id=1;
        String name="Rahul Basutkar";
        String email="rahulbasutkar33@gmail.com";
        UserController userController=new UserController();
        Response expected=new Response(Http.Ok,"User Created..");

        // act
         Response actual=userController.createUser(id,name,email);

        // assert
        assertEquals(expected,actual);

    }

}
