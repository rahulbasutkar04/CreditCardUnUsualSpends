package com.amaap.creditcardunusualspends.domain.model.entity;

import com.amaap.creditcardunusualspends.domain.model.Categories;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private long creditCardNumber;
    private Date date;
    private Categories category;
    private long amount;

    public Transaction(long creditCardNumber, Date date, Categories category, long amount) {
        this.creditCardNumber = creditCardNumber;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return creditCardNumber == that.creditCardNumber && amount == that.amount && Objects.equals(date, that.date) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber, date, category, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "creditCardNumber=" + creditCardNumber +
                ", date=" + date +
                ", category=" + category +
                ", amount=" + amount +
                '}';
    }
}
