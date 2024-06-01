package com.amaap.creditcardunusualspends.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class InMemoryCreditCardRepositoryTest {

    private Database database;
    private InMemoryCreditCardRepository repository;

    @BeforeEach
    void setUp() {
        database = mock(Database.class);
        repository = new InMemoryCreditCardRepository(database);
    }

    @Test
    void shouldBeAbleToAddCreditCardData() {
        CreditCard creditCard = new CreditCard(1);
        repository.addCreditCardData(creditCard);
        verify(database).insertIntoCreditCardData(creditCard);
    }

    @Test
    void shouldBeAbleToGetCreditCardsIfCardIsEmpty() {
        when(database.getCreditCardData()).thenReturn(Collections.emptyList());
        assertTrue(repository.getCreditCards().isEmpty());
    }

    @Test
    void shouldBeAbleToGetCreditCardsIfCardIsNonEmpty() {
        CreditCard creditCard = new CreditCard(1);
        when(database.getCreditCardData()).thenReturn(Collections.singletonList(creditCard));
        assertEquals(1, repository.getCreditCards().size());
    }

    @Test
    void shouldBeAbleToReturnTrueIfCreditCardPresent() {
        when(database.isCreditCardPresent(123456789L)).thenReturn(true);
        assertTrue(repository.isCreditCardPresent(123456789L));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCreditCardNotPresent() {
        when(database.isCreditCardPresent(987654321L)).thenReturn(false);
        assertFalse(repository.isCreditCardPresent(987654321L));
    }

    @Test
    void shouldBeAbleToGetUserIdByCreditCardNumber() {
        CreditCard creditCard = new CreditCard(1);
        when(database.getCreditCardData()).thenReturn(Collections.singletonList(creditCard));
        assertEquals(1, repository.getUserIdByCreditCardNumber(creditCard.getCreditCardNumber()));
    }

    @Test
    void ShouldThrowExceptionIfUserIdByCreditCardNumberNotPresent() {
        when(database.getCreditCardData()).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> repository.getUserIdByCreditCardNumber(123456789L));
    }
}
