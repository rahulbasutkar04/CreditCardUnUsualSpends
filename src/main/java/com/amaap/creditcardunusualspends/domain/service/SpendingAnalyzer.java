package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface SpendingAnalyzer {
    List<Map<String, Object>> findUnusualSpends(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage);
}