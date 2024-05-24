package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Inject;

import java.util.Map;

public class InMemoryExpenditureRepository implements ExpenditureRepository {
    private final FakeDatabase fakeDatabase;

    @Inject
    public InMemoryExpenditureRepository(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    @Override
    public void addUnUsualSpendData(Map<Categories, Double> unusualSpendData) {
        fakeDatabase.insertIntoExpenditureDataTable(unusualSpendData);
    }
}
