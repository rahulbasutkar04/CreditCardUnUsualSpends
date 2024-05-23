package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private final Date date;
    private final Categories categories;
    private final double amount;

    private Transaction(Date date, Categories categories, double amount) {
        this.date = date;
        this.categories = categories;
        this.amount = amount;
    }

    public static Transaction createTransaction(Date date, Categories categories, double amount) throws IllegalAmountException {
        if (amount < 0) {
            throw new IllegalAmountException("Amount cannot be negative");
        }

        System.out.println("Transaction Performed..");
        return new Transaction(date, categories, amount);
    }

    public Date getDate() {
        return date;
    }

    public Categories getCategories() {
        return categories;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date) && categories == that.categories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, categories, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", categories=" + categories +
                ", amount=" + amount +
                '}';
    }
}
