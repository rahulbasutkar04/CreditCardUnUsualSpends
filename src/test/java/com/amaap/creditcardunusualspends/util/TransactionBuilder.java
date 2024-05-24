package com.amaap.creditcardunusualspends.util;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.amaap.creditcardunusualspends.domain.service.Transaction.createTransaction;

public class TransactionBuilder {
    List<Transaction> transactions=new ArrayList<>();

   public List<Transaction> currentMonthTransactionData() throws IllegalAmountException {
        transactions.add(createTransaction(new Date(), Categories.FOOD, 100.0));
        transactions.add(createTransaction(new Date(), Categories.GROCERY, 200.0));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date previousMonthDate = calendar.getTime();
        transactions.add(createTransaction(previousMonthDate, Categories.ENTERTAINMENT, 150.0));
        return transactions;
    }
    public List<Transaction> previousMonthTransactionData() throws IllegalAmountException {
        transactions.add(createTransaction(new Date(), Categories.FOOD, 100.0));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date previousMonthDate = calendar.getTime();
        transactions.add(createTransaction(previousMonthDate, Categories.GROCERY, 200.0));
        transactions.add(createTransaction(previousMonthDate, Categories.ENTERTAINMENT, 150.0));
        return transactions;
    }
    public List<Transaction> allTransactionData() throws IllegalAmountException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date previousMonthDate = calendar.getTime();
        Date currentMonthDate = new Date();
        transactions.add(createTransaction(currentMonthDate, Categories.FOOD, 300.0));
        transactions.add(createTransaction(currentMonthDate, Categories.GROCERY, 450.0));
        transactions.add(createTransaction(previousMonthDate, Categories.FOOD, 100.0));
        transactions.add(createTransaction(previousMonthDate, Categories.GROCERY, 200.0));
        transactions.add(createTransaction(previousMonthDate, Categories.ENTERTAINMENT, 150.0));
        return transactions;
    }
}
