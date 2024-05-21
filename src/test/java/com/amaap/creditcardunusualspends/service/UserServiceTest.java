package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        userService = injector.getInstance(UserService.class);
    }

    @Test
    void shouldBeAbleToReturnTrueIfUserGetCreatedAndSaved() throws InvalidUserIdException, InvalidUserNameException, InvalidUserException, InvalidEmailException {
        // arrange
        int id = 1;
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";

        // act & assert
        assertTrue(userService.createUser(id, name, email));

    }

    @Test
    void shouldThrowExceptionIfIdIsWrong() {
        assertThrows(InvalidUserIdException.class, () -> {
            userService.createUser(-1, "john doe", "abc@gmail.com");
        });
    }


    @Test
    void shouldThrowExceptionIfNameISNull() {
        assertThrows(InvalidUserNameException.class, () -> {
            userService.createUser(1, null, "abc@gmail.com");
        });
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        assertThrows(InvalidEmailException.class, () -> {
            userService.createUser(1, "Rahul", null);
        });
    }


}