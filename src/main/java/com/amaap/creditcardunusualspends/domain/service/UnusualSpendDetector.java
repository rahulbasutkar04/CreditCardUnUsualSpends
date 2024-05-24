package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;

import java.util.Map;

public interface UnusualSpendDetector {
    Map<Categories, Double> findUnusualSpends(Map<Categories, Double> currentMonthSpending, Map<Categories, Double> previousMonthSpending);
}
