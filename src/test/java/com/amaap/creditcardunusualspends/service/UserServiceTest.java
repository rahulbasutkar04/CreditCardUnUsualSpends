package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        userRepository = injector.getInstance(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldBeAbleToReturnTrueIfUserGetCreatedAndSaved() throws CreditCardException {
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

    @Test
    void shouldThrowExceptionIfUserIdIsDuplicate() throws CreditCardException {
        // arrange
        int id = 1;
        String name1 = "Rahul Basutkar";
        String email1 = "rahulbasutkar33@gmail.com";

        String name2 = "John Doe";
        String email2 = "john.doe@example.com";

        // act
        userService.createUser(id, name1, email1);

        // assert
        assertThrows(DuplicateUserIdException.class, () -> {
            userService.createUser(id, name2, email2);
        });
    }


}