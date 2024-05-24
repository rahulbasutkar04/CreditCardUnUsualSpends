package com.amaap.creditcardunusualspends.repository;

import com.amaap.creditcardunusualspends.domain.model.Categories;

import java.util.Map;

public interface ExpenditureRepository {
    void addUnUsualSpendData(Map<Categories,Double> unusualSpendData);
}
