package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnusualSpendAnalyser {
    private final UnusualSpendDetector unusualSpendDetector;

    public UnusualSpendAnalyser(UnusualSpendDetector unusualSpendDetector) {
        this.unusualSpendDetector = unusualSpendDetector;
    }

    private static Map<Categories, Double> calculateTotalSpendingPerCategory(List<Transaction> transactions) {
        Map<Categories, Double> spendingPerCategory = new HashMap<>();

        for (Transaction transaction : transactions) {
            Categories category = transaction.getCategories();
            Double amount = transaction.getAmount();
            spendingPerCategory.put(category, spendingPerCategory.getOrDefault(category, 0.0) + amount);
        }
        return spendingPerCategory;
    }

    public Map<Categories, Double> calculateUnusualSpends(List<Transaction> transactionData) {
        List<Transaction> currentMonthData = CurrentMonthTransactionAnalyser.getCurrentMonthTransactions(transactionData);
        List<Transaction> previousMonthData = LastMonthTransactionAnalyser.getLastMonthTransactions(transactionData);
        Map<Categories, Double> currentMonthSpending = calculateTotalSpendingPerCategory(currentMonthData);
        Map<Categories, Double> previousMonthSpending = calculateTotalSpendingPerCategory(previousMonthData);
        return unusualSpendDetector.findUnusualSpends(currentMonthSpending, previousMonthSpending);
    }
}
