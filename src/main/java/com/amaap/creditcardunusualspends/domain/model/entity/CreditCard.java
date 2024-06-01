package com.amaap.creditcardunusualspends.domain.model.entity;

import java.util.Objects;
import java.util.Random;

public class CreditCard {
    private int userId;
    private long creditCardNumber;

    public CreditCard(int userId) {
        this.userId = userId;
        this.creditCardNumber = generateCreditCardNumber();
    }


    private long generateCreditCardNumber() {
        Random random = new Random();
        return 10000000L + random.nextInt(90000000);
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard that)) return false;
        return userId == that.userId && creditCardNumber == that.creditCardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, creditCardNumber);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "userId=" + userId +
                ", creditCardNumber=" + creditCardNumber +
                '}';
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
