package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.amaap.creditcardunusualspends.repository.impl.db.impl.InMemoryFakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryExpenditureRepositoryTest {

    private Database database;
    private InMemoryExpenditureRepository repository;

    @BeforeEach
    public void setUp() {
        database = new InMemoryFakeDatabase();
        repository = new InMemoryExpenditureRepository(database);
    }

    @Test
    public void shouldBeAbleToTestAddIntoExpenditureData() {
        List<Map<String, Object>> spendData = List.of(
                Map.of("id", 1, "amount", 100),
                Map.of("id", 2, "amount", 200)
        );

        repository.addIntoExpenditureData(spendData);

        List<Map<String, Object>> storedData = database.getSpendsData();
        assertEquals(spendData, storedData);
    }

    @Test
    public void shouldBeAbleToTestGetSpendData() {
        List<Map<String, Object>> spendData = List.of(
                Map.of("id", 1, "amount", 100),
                Map.of("id", 2, "amount", 200)
        );

        database.insertIntoExpenditureTable(spendData);

        List<Map<String, Object>> retrievedData = repository.getSpendData();
        assertEquals(spendData, retrievedData);
    }
}
