package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.domain.model.valueobject.SpendCategory;
import com.amaap.creditcardunusualspends.util.DateBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryFakeDatabaseTest {

    private InMemoryFakeDatabase database;
    DateBuilder dateBuilder=new DateBuilder();

    @BeforeEach
    public void setUp() {
        database = new InMemoryFakeDatabase();
    }

    @Test
    public void shouldBeAbleToInsertAndRetrieveCustomer() {
        // arrange
        Customer customer = new Customer("Rahul Basutkar", "rahul.basutkar@example.com");

        // act
        database.insertIntoCustomerTable(customer);
        List<Customer> customers = database.getCustomerList();

        // assert
        assertEquals(1, customers.size());
        assertEquals("Rahul Basutkar", customers.get(0).getName());
        assertEquals("rahul.basutkar@example.com", customers.get(0).getEmail());
    }

    @Test
    public void shouldBeABlrToInsertAndRetrieveCreditCard() {
        // arrange
        CreditCard creditCard = new CreditCard(1);

        // act
        database.insertIntoCreditCardData(creditCard);
        List<CreditCard> creditCards = database.getCreditCardData();

        // assert
        assertEquals(1, creditCards.size());
    }

    @Test
    public void shouldBeAbleToInsertAndRetrieveTransaction() {
        // arrange
        Transaction transaction = new Transaction(112345, dateBuilder.createDate(2024,01,14), SpendCategory.GROCERIES,200);

        // act
        database.insertIntoTransactionTable(transaction);
        List<Transaction> transactions = database.getTransactions();

        // assert
        assertEquals(1, transactions.size());
        assertEquals(transaction.getCreditCardNumber(), transactions.get(0).getCreditCardNumber());
        assertEquals(200.0, transactions.get(0).getAmount());
    }

    @Test
    public void ShouldBeAbleToCheckIsCreditCardPresent() {
        // arrange
        CreditCard creditCard = new CreditCard(1);

        // act
        database.insertIntoCreditCardData(creditCard);
        boolean isPresent = database.isCreditCardPresent(creditCard.getCreditCardNumber());

        // assert
        assertTrue(isPresent);
    }

    @Test
    public void ShouldBeAbleToInsertAndRetrieveSpendData() {
        // arrange
        Map<String, Object> spendData1 = new HashMap<>();
        spendData1.put("category", "Travel");
        spendData1.put("extraAmount", 100.0);
        Map<String, Object> spendData2 = new HashMap<>();
        spendData2.put("category", "Food");
        spendData2.put("extraAmount", 50.0);

        List<Map<String, Object>> spendDataList = List.of(spendData1, spendData2);

        // act
        database.insertIntoExpenditureTable(spendDataList);
        List<Map<String, Object>> retrievedSpendDataList = database.getSpendsData();

        // assert
        assertEquals(2, retrievedSpendDataList.size());
        assertEquals("Travel", retrievedSpendDataList.get(0).get("category"));
        assertEquals(100.0, retrievedSpendDataList.get(0).get("extraAmount"));
        assertEquals("Food", retrievedSpendDataList.get(1).get("category"));
        assertEquals(50.0, retrievedSpendDataList.get(1).get("extraAmount"));
    }



    @Test
    public void shouldBeAbleToHandleEmptySpendData() {
        // arrange
        List<Map<String, Object>> spendDataList = List.of();

        // act
        database.insertIntoExpenditureTable(spendDataList);
        List<Map<String, Object>> retrievedSpendDataList = database.getSpendsData();

        // assert
        assertEquals(0, retrievedSpendDataList.size());
    }

    @Test
    public void shouldBeAbleToClearDatabase() {
        // arrange
        database.insertIntoCustomerTable(new Customer("John Doe", "john@example.com"));
        database.insertIntoCreditCardData(new CreditCard(1));
        database.insertIntoTransactionTable(new Transaction(123456789L, null, null, 0));
        database.insertIntoExpenditureTable(List.of(Map.of("category", "Travel", "extraAmount", 100.0)));

        // act
        database.clear();

        // assert
        assertTrue(database.getCustomerList().isEmpty());
        assertTrue(database.getCreditCardData().isEmpty());
        assertTrue(database.getTransactions().isEmpty());
        assertTrue(database.getSpendsData().isEmpty());
    }
}
