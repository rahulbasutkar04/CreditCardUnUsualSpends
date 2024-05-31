package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PreviousMonthDataExtractor {
    public List<Transaction> getPreviousMonthTransactions(List<Transaction> transactionData) {
        List<Transaction> previousMonthTransactions = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int previousMonth = calendar.get(Calendar.MONTH);
        int previousYear = calendar.get(Calendar.YEAR);

        for (Transaction transaction : transactionData) {
            Date transactionDate = transaction.getDate();
            Calendar transactionCalendar = Calendar.getInstance();
            transactionCalendar.setTime(transactionDate);

            int transactionMonth = transactionCalendar.get(Calendar.MONTH);
            int transactionYear = transactionCalendar.get(Calendar.YEAR);

            if (transactionYear == previousYear && transactionMonth == previousMonth) {
                previousMonthTransactions.add(transaction);
            }
        }

        return previousMonthTransactions;
    }
}
