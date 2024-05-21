package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService=new UserService();

    @Test
    void shouldBeAbleToReturnTrueIfUserGetCreatedAndSaved() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException {
        // arrange
        int id=1;
        String name="Rahul Basutkar";
        String email="rahulbasutkar33@gmail.com";

        // act & assert
       assertTrue(userService.CreateUser(id,name,email));

    }

    @Test
    void shouldThrowExceptionIfIdIsWrong() {
        assertThrows(InvalidUserIdException.class, () -> {
            userService.CreateUser(-1, "john doe", "abc@gmail.com");
        });
    }


    @Test
    void shouldThrowExceptionIfNameISNull() {
         assertThrows(InvalidUserNameException.class, () -> {
            userService.CreateUser(1, null, "abc@gmail.com");
        });
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        assertThrows(InvalidEmailException.class, () -> {
            userService.CreateUser(1, "Rahul", null);
        });
    }


}