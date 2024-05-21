package com.amaap.creditcardunusualspends.service.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {


    @Test
    void ShouldAbleToCheckValidCustomerNames()
    {
        UserValidator  userValidator=new UserValidator() ;
        boolean actual=userValidator.isValidName("rahul");
        assertTrue(actual);
        assertTrue( userValidator.isValidName("rahul basutkar"));
        assertTrue(userValidator.isValidName("RahulBasutkar"));
        assertTrue(userValidator.isValidName("Rah bas"));
        assertTrue(userValidator.isValidName("RAHUL BASUTKAR"));
        assertTrue(userValidator.isValidName("RAHUL Sanjay Basutkar"));

        assertFalse(userValidator.isValidName("RAHUL      Sanjay          Basutkar"));
        assertFalse(userValidator.isValidName("1234"));
        assertFalse(userValidator.isValidName("Rah_bas"));
        assertFalse(userValidator.isValidName("     Rahbas"));
        assertFalse(userValidator.isValidName(null));
        assertFalse(userValidator.isValidName(""));
        assertFalse(userValidator.isValidName("123"));
        assertFalse(userValidator.isValidName("Ra"));
        assertFalse(userValidator.isValidName("Rahul B."));
        assertFalse(userValidator.isValidName("Rahul     Basutkar"));
        assertFalse(userValidator.isValidName("Rahul     Basutkar123"));
        assertFalse(userValidator.isValidName("RahulBasutkar123"));
        assertFalse(userValidator.isValidName("RahulBasutkar12  3    123"));
        assertFalse(userValidator.isValidName("RahulBasutkar12  vasant  Basutkar"));

    }

    @Test
    void shouldAbleToValidEmailAddresses()
    {
        UserValidator  userValidator=new UserValidator() ;

        assertTrue(userValidator.isValidEmail("rahulbasutkar33@gmail.com"));
        assertTrue(userValidator.isValidEmail("rahulb@gmail.comm"));
        assertTrue(userValidator.isValidEmail("rahulb123@gmail.comm"));
        assertTrue(userValidator.isValidEmail("user@example.com"));
        assertTrue(userValidator.isValidEmail("user.name@example.com"));
        assertTrue(userValidator.isValidEmail("user123@example-domain.com"));
        assertTrue(userValidator.isValidEmail("user-name@example.co.uk"));

        assertFalse(userValidator.isValidEmail("user@localhost"));
        assertFalse(userValidator.isValidEmail("user@.com"));
        assertFalse(userValidator.isValidEmail("@example.com"));
        assertFalse(userValidator.isValidEmail("user@example."));
        assertFalse(userValidator.isValidEmail("rahulb12gmail.com"));
        assertFalse(userValidator.isValidEmail("user@example.com."));
        assertFalse(userValidator.isValidEmail("user@example..com"));
        assertFalse(userValidator.isValidEmail("user@example..co.uk"));
        assertFalse(userValidator.isValidEmail("user@example.."));
        assertFalse(userValidator.isValidEmail("user@123"));
        assertFalse(userValidator.isValidEmail("user@-"));
        assertFalse(userValidator.isValidEmail("user@."));
        assertFalse(userValidator.isValidEmail("user@"));
        assertFalse(userValidator.isValidEmail(""));

    }

}