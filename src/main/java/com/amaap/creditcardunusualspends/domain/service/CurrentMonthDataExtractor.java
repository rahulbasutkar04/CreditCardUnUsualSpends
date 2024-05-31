package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentMonthDataExtractor {

    public List<Transaction> getCurrentMonthTransactions(List<Transaction> transactionData) {
        List<Transaction> currentMonthTransactions = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        for (Transaction transaction : transactionData) {
            Date transactionDate = transaction.getDate();
            Calendar transactionCalendar = Calendar.getInstance();
            transactionCalendar.setTime(transactionDate);

            int transactionMonth = transactionCalendar.get(Calendar.MONTH);
            int transactionYear = transactionCalendar.get(Calendar.YEAR);

            if (transactionYear == currentYear && transactionMonth == currentMonth) {
                currentMonthTransactions.add(transaction);
            }
        }

        return currentMonthTransactions;
    }
}
