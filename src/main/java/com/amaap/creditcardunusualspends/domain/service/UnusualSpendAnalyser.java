package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.List;
import java.util.Map;

public class UnusualSpendAnalyser {

    CurrentMonthDataExtractor currentMonthDataExtractor=new CurrentMonthDataExtractor();
    PreviousMonthDataExtractor previousMonthDataExtractor=new PreviousMonthDataExtractor();
    private final Analyzer analyzer=new Analyzer();

    public List<Map<String, Object>> getSpends(List<Transaction> transactionData) {
        List<Transaction> currentMonthTransactions=currentMonthDataExtractor.getCurrentMonthTransactions(transactionData);
        List<Transaction> previousMonthTransactions=previousMonthDataExtractor.getPreviousMonthTransactions(transactionData);
       return  analyzer.findUnusualSpends(currentMonthTransactions,previousMonthTransactions,50) ;
    }
}
