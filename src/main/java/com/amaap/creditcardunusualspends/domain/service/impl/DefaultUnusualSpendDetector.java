package com.amaap.creditcardunusualspends.domain.service.impl;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendDetector;

import java.util.HashMap;
import java.util.Map;

public class DefaultUnusualSpendDetector implements UnusualSpendDetector {
    private final double thresholdPercentage;
    public DefaultUnusualSpendDetector(double thresholdPercentage) {
        this.thresholdPercentage = thresholdPercentage;
    }

    @Override
    public Map<Categories, Double> findUnusualSpends(Map<Categories, Double> currentMonthSpending, Map<Categories, Double> previousMonthSpending) {
        Map<Categories, Double> unusualSpends = new HashMap<>();

        for (Map.Entry<Categories, Double> entry : currentMonthSpending.entrySet()) {
            Categories category = entry.getKey();
            Double currentMonthAmount = entry.getValue();
            Double previousMonthAmount = previousMonthSpending.getOrDefault(category, 0.0);
            if (previousMonthAmount > 0 && currentMonthAmount >= (1 + thresholdPercentage / 100) * previousMonthAmount) {
                unusualSpends.put(category, currentMonthAmount);
            }
        }

        return unusualSpends;
    }
}
