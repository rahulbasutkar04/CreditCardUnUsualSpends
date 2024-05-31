package com.amaap.creditcardunusualspends.domain.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditCardTest {

    private CreditCard creditCard1;
    private CreditCard creditCard2;

    @BeforeEach
    void setUp() {
        creditCard1 = new CreditCard(1);
        creditCard2 = new CreditCard(2);
    }

    @Test
    void shouldBeAbleToCreateCreditCardWithUserId() {
        assertEquals(1, creditCard1.getUserId());
        assertTrue(creditCard1.getCreditCardNumber() >= 10000000L && creditCard1.getCreditCardNumber() < 100000000L);

        assertEquals(2, creditCard2.getUserId());
        assertTrue(creditCard2.getCreditCardNumber() >= 10000000L && creditCard2.getCreditCardNumber() < 100000000L);
    }

    @Test
    void shouldBeAbleToGetUserId() {
        assertEquals(1, creditCard1.getUserId());
        assertEquals(2, creditCard2.getUserId());
    }

    @Test
    void shouldBeAbleToGetCreditCardNumber() {
        assertTrue(creditCard1.getCreditCardNumber() >= 10000000L && creditCard1.getCreditCardNumber() < 100000000L);
        assertTrue(creditCard2.getCreditCardNumber() >= 10000000L && creditCard2.getCreditCardNumber() < 100000000L);
    }

    @Test
    void shouldBeAbleToCheckEquality() {
        CreditCard anotherCreditCard1 = new CreditCard(1);

        assertEquals(creditCard1, creditCard1);

        assertNotEquals(creditCard1, anotherCreditCard1);

        assertNotEquals(creditCard1, creditCard2);

        assertNotEquals(creditCard1, null);

        assertNotEquals(creditCard1, new Object());
    }

    @Test
    void shouldBeAbleToGenerateConsistentHashCode() {
        CreditCard anotherCreditCard1 = new CreditCard(1);

        assertEquals(creditCard1.hashCode(), creditCard1.hashCode());

        assertNotEquals(creditCard1.hashCode(), anotherCreditCard1.hashCode());
    }

    @Test
    void shouldBeAbleToGenerateToString() {
        String expectedString = "CreditCard{userId=" + creditCard1.getUserId() + ", creditCardNumber=" + creditCard1.getCreditCardNumber() + "}";
        assertEquals(expectedString, creditCard1.toString());
    }
}
