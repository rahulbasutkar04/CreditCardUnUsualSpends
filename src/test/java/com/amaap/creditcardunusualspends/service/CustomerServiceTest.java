package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.service.exception.CustomerException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCustomerNameException;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        customerService = injector.getInstance(CustomerService.class);

    }

    @Test
    void shouldBeAbleToReturnTrueIfUserGetCreatedAndSaved() throws CustomerException {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";

        // act & assert
        assertTrue(customerService.create(name, email));

    }

    @Test
    void shouldThrowExceptionIfNameISNull() {
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create(null, "abc@gmail.com");
        });
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        assertThrows(InvalidEmailException.class, () -> {
            customerService.create("Rahul", null);
        });
    }


    @Test
    void shouldThrowExceptionIfNameIsInNotValidFormat() {
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("34566", "rahulb@gmail.com");
        });
    }

    @Test
    void shouldThrowExceptionIEmailIsInNotValidFormat() {
        assertThrows(InvalidEmailException.class, () -> {
            customerService.create("Rahul", "12345");
        });
    }


}