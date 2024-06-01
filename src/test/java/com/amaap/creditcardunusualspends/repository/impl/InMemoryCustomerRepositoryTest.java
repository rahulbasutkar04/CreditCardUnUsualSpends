package com.amaap.creditcardunusualspends.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class InMemoryCustomerRepositoryTest {

    private Database database;
    private InMemoryCustomerRepository repository;

    @BeforeEach
    void setUp() {
        database = mock(Database.class);
        repository = new InMemoryCustomerRepository(database);
    }

    @AfterEach
    void tearDown() {
        database.clear();
    }


    @Test
    void shouldBeAbleToAddCustomerData() {
        Customer customer = new Customer( "John Doe", "john@example.com");
        repository.addCustomerData(customer);
        verify(database).insertIntoCustomerTable(customer);
    }

    @Test
    void shouldBeAbleToGetCustomer_empty() {
        when(database.getCustomerList()).thenReturn(Collections.emptyList());
        assertTrue(repository.getCustomer().isEmpty());
    }

    @Test
    void shouldBeAbleToGetCustomer_nonEmpty() {
        Customer customer = new Customer( "John Doe", "john@example.com");
        when(database.getCustomerList()).thenReturn(Collections.singletonList(customer));
        assertEquals(1, repository.getCustomer().size());
    }

    @Test
    void shouldBeAbleToFindCustomerByNameAndEmail_found() {
        Customer customer = new Customer( "John Doe", "john@example.com");
        when(database.getCustomerList()).thenReturn(Collections.singletonList(customer));
        assertEquals(customer, repository.findCustomerByNameAndEmail("John Doe", "john@example.com"));
    }

    @Test
    void shouldBeAbleToFindCustomerByNameAndEmail_notFound() {
        when(database.getCustomerList()).thenReturn(Collections.emptyList());
        assertNull(repository.findCustomerByNameAndEmail("John Doe", "john@example.com"));
    }

    @Test
    void shouldBeAbleToGetCustomerById_found() {
        Customer customer = new Customer( "John Doe", "john@example.com");
        when(database.getCustomerList()).thenReturn(Collections.singletonList(customer));
        assertEquals(customer, repository.getCustomerById(customer.getId()));
    }

    @Test
    void shouldBeAbleToGetCustomerById_notFound() {
        when(database.getCustomerList()).thenReturn(Collections.emptyList());
        assertNull(repository.getCustomerById(1));
    }
}
