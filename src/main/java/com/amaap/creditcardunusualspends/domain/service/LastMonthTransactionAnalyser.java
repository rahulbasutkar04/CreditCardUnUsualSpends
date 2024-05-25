package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LastMonthTransactionAnalyser {
    public static List<Transaction> getLastMonthTransactions(List<Transaction> transactionData) {
        List<Transaction> previousMonthTransactions = new ArrayList<>();
        Calendar currentCalendar = Calendar.getInstance();
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        int previousMonth = currentMonth == 0 ? 11 : currentMonth - 1;
        int previousMonthYear = currentMonth == 0 ? currentYear - 1 : currentYear;

        for (Transaction transaction : transactionData) {
            Date transactionDate = transaction.getDate();
            Calendar transactionCalendar = Calendar.getInstance();
            transactionCalendar.setTime(transactionDate);

            int transactionMonth = transactionCalendar.get(Calendar.MONTH);
            int transactionYear = transactionCalendar.get(Calendar.YEAR);

            if (transactionMonth == previousMonth && transactionYear == previousMonthYear) {
                previousMonthTransactions.add(transaction);
            }
        }
        return previousMonthTransactions;
    }
}
