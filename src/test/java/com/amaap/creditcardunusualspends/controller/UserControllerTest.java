package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.service.UserService;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    private UserController userController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        UserService userService = injector.getInstance(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void shouldBeAbleToCreateCustomerAndRespond() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException {
        // arrange
        int id = 1;
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";
        Response expected = new Response(Http.OK, "User Created..");

        // act
        Response actual = userController.createUser(id, name, email);

        // assert
        assertEquals(expected, actual);

    }

}
