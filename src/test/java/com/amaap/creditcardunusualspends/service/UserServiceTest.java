package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    void shouldBeAbleToReturnTrueIfUserGetCreatedAndSaved() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException {
        // arrange
        UserService userService=new UserService();
        int id=1;
        String name="Rahul Basutkar";
        String email="rahulbasutkar33@gmail.com";

        // act & assert
       assertTrue(userService.CreateUser(id,name,email));

    }

}