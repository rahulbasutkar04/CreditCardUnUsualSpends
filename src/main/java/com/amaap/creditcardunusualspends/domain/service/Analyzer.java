package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyzer implements SpendingAnalyzer {

    @Override
    public List<Map<String, Object>> findUnusualSpends(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        Map<String, Double> currentMonthSpending = calculateCategorySpending(currentMonthTransactions);
        Map<String, Double> previousMonthSpending = calculateCategorySpending(previousMonthTransactions);


        List<Map<String, Object>> unusualSpends = new ArrayList<>();

        for (Map.Entry<String, Double> entry : currentMonthSpending.entrySet()) {
            String category = entry.getKey();
            double currentAmount = entry.getValue();
            double previousAmount = previousMonthSpending.getOrDefault(category, 0.0);

            if (previousAmount > 0) {
                double percentageIncrease = (currentAmount - previousAmount) / previousAmount * 100;
                if (percentageIncrease >= thresholdPercentage) {
                    Map<String, Object> unusualSpend = new HashMap<>();
                    unusualSpend.put("category", category);
                    unusualSpend.put("extraAmount", currentAmount - previousAmount);
                    unusualSpends.add(unusualSpend);
                }
            }
        }

        return unusualSpends;
    }

    private Map<String, Double> calculateCategorySpending(List<Transaction> transactions) {
        Map<String, Double> categorySpending = new HashMap<>();

        for (Transaction transaction : transactions) {
            String category = transaction.getCategory().name();
            double amount = transaction.getAmount();
            categorySpending.put(category, categorySpending.getOrDefault(category, 0.0) + amount);
        }

        return categorySpending;
    }
}