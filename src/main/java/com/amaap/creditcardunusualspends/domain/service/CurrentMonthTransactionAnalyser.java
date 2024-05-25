package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentMonthTransactionAnalyser {
    public static List<Transaction> getCurrentMonthTransactions(List<Transaction> transactionData) {
        List<Transaction> currentMonthTransactions = new ArrayList<>();

        Calendar currentCalendar = Calendar.getInstance();
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        for (Transaction transaction : transactionData) {
            Date transactionDate = transaction.getDate();
            Calendar transactionCalendar = Calendar.getInstance();
            transactionCalendar.setTime(transactionDate);

            int transactionMonth = transactionCalendar.get(Calendar.MONTH);
            int transactionYear = transactionCalendar.get(Calendar.YEAR);

            if (transactionMonth == currentMonth && transactionYear == currentYear) {
                currentMonthTransactions.add(transaction);
            }
        }
        return currentMonthTransactions;
    }
}
