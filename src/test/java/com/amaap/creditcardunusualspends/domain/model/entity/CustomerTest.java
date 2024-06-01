package com.amaap.creditcardunusualspends.domain.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void shouldBeAbleToInstantiateCustomer() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";

        // act
        Customer customer = new Customer(name, email);

        // assert
        assertNotNull(customer);
        assertEquals(name, customer.getName());
        assertEquals(email, customer.getEmail());
    }

    @Test
    void shouldBeAbleToGetCustomerId() {
        // arrange
        String name1 = "Rahul Basutkar";
        String email1 = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer(name1, email1);
        String name2 = "John Mary";
        String email2 = "john.doe@example.com";
        Customer customer2 = new Customer(name2, email2);

        // act
        int id1 = customer1.getId();
        int id2 = customer2.getId();

        // assert
        assertTrue(id1 > 0, "Customer ID should be greater than 0");
        assertTrue(id2 > id1, "Customer ID should be incremented sequentially");
    }

    @Test
    void shouldBeAbleToCheckEquality() {
        // arrange
        String name1 = "Rahul Basutkar";
        String email1 = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer(name1, email1);
        Customer customer2 = new Customer(name1, email1);

        // act & assert
        assertNotEquals(customer1, customer2);
    }

    @Test
    void shouldBeAbleToGenerateConsistentHashCode() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer(name, email);
        Customer customer2 = new Customer(name, email);

        // act
        int hashCode1 = customer1.hashCode();
        int hashCode2 = customer2.hashCode();

        // assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void shouldBeAbleToCheckInequality() {
        // arrange
        Customer customer1 = new Customer("Rahul Basutkar", "rahulbasutkar33@gmail.com");
        Customer customer2 = new Customer("John Mary", "john.doe@example.com");

        // act & assert
        assertNotEquals(customer1, customer2);
    }

    @Test
    void shouldBeAbleToHandleNullEquality() {
        // arrange
        Customer customer = new Customer("Rahul Basutkar", "rahulbasutkar33@gmail.com");

        // act & assert
        assertNotEquals(customer, null);
    }

    @Test
    void shouldBeAbleToReturnTrueForSameObjectEquality() {
        // arrange
        Customer customer = new Customer("Rahul Basutkar", "rahulbasutkar33@gmail.com");

        // act & assert
        assertEquals(customer, customer);
    }

    @Test
    void shouldBeAbleToHandleDifferentClassEquality() {
        // arrange
        Customer customer = new Customer("Rahul Basutkar", "rahulbasutkar33@gmail.com");
        String notACustomer = "Not a Customer Object";

        // act & assert
        assertNotEquals(customer, notACustomer);
    }

    @Test
    void shouldBeAbleToGenerateConsistentToString() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";
        Customer customer = new Customer(name, email);

        // act
        String customerString = customer.toString();

        // assert
        assertEquals("Customer{id=" + customer.getId() + ", name='Rahul Basutkar', email='rahulbasutkar33@gmail.com'}", customerString);
    }

    @Test
    void shouldBeAbleToGenerateDifferentIdsForDifferentInstances() {
        // arrange
        Customer customer1 = new Customer("Rahul Basutkar", "rahulbasutkar33@gmail.com");
        Customer customer2 = new Customer("John Mary", "john.doe@example.com");

        // act
        int id1 = customer1.getId();
        int id2 = customer2.getId();

        // assert
        assertNotEquals(id1, id2);
    }

    @Test
    void shouldBeAbleToHandleEmptyNameAndEmail() {
        // arrange
        Customer customer = new Customer("", "");

        // act & assert
        assertEquals("", customer.getName());
        assertEquals("", customer.getEmail());
    }

    @Test
    void shouldBeAbleToConsiderCustomersWithSameIdNameEmailAsEqual() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer(name, email);
        Customer customer2 = new Customer(name, email);

        try {
            var field = Customer.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(customer2, customer1.getId());
        } catch (Exception e) {
            fail("Failed to set customer ID for test");
        }

        // act & assert
        assertEquals(customer1, customer2);
    }

    @Test
    void shouldNotConsiderCustomersWithDifferentIdsAsEqual() {
        // arrange
        String name = "Rahul Basutkar";
        String email = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer(name, email);
        Customer customer2 = new Customer(name, email);

        // act & assert
        assertNotEquals(customer1, customer2);
    }

    @Test
    void shouldNotConsiderCustomersWithDifferentNamesAsEqual() {
        // arrange
        String email = "rahulbasutkar33@gmail.com";
        Customer customer1 = new Customer("Rahul Basutkar", email);
        Customer customer2 = new Customer("John Mary", email);

        try {
            var field = Customer.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(customer2, customer1.getId());
        } catch (Exception e) {
            fail("Failed to set customer ID for test");
        }

        // act & assert
        assertNotEquals(customer1, customer2);
    }

    @Test
    void shouldNotConsiderCustomersWithDifferentEmailsAsEqual() {
        // arrange
        String name = "Rahul Basutkar";
        Customer customer1 = new Customer(name, "rahulbasutkar33@gmail.com");
        Customer customer2 = new Customer(name, "john.doe@example.com");

        try {
            var field = Customer.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(customer2, customer1.getId());
        } catch (Exception e) {
            fail("Failed to set customer ID for test");
        }

        // act & assert
        assertNotEquals(customer1, customer2);
    }

}
