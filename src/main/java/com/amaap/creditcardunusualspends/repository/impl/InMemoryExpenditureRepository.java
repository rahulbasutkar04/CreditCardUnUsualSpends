package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class InMemoryExpenditureRepository implements ExpenditureRepository {
    private Database database;

    @Inject
    public InMemoryExpenditureRepository(Database database) {
        this.database = database;
    }

    @Override
    public void addIntoExpenditureData(List<Map<String, Object>> spendData) {
        database.insertIntoExpenditureTable(spendData);
    }

    @Override
    public List<Map<String, Object>> getSpendData() {
        return database.getSpendsData();
    }


}
