package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryCreditCardRepositoryTest {

    private InMemoryCreditCardRepository creditCardRepository;
    private FakeDatabase fakeDatabase;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        fakeDatabase = injector.getInstance(FakeDatabase.class);
        creditCardRepository = new InMemoryCreditCardRepository(fakeDatabase);
    }

    @Test
    void shouldBeAbleToAddCreditCardDetails() {
        // arrange
        int userId = 1;
        long creditCardNumber = 12345678;

        // act
        creditCardRepository.addCreditCardDetails(userId, creditCardNumber);

        // assert
        assertEquals(creditCardNumber, fakeDatabase.getCreditCardData().get(userId));
    }

    @Test
    void shouldBeAbleToReturnCreditCardDetails() {
        // arrange
        int userId = 1;
        long creditCardNumber = 1234567812345678L;
        fakeDatabase.InsertIntoCreditCardTable(userId, creditCardNumber);

        // act
        Map<Integer, Long> creditCardDetails = creditCardRepository.getCreditCardDetails();

        // assert
        assertEquals(1, creditCardDetails.size());
        assertEquals(creditCardNumber, creditCardDetails.get(userId));
    }

    @Test
    void shouldThrowExceptionIfNoUserIsCreatedAndAskedForTheirCreditCardNumber() {
        // act & assert
        assertThrows(IllegalStateException.class,()->{
                creditCardRepository.getCreditCardNumber();
        });


    }
}
