package com.amaap.creditcardunusualspends.domain;

import java.util.Objects;
import java.util.Random;

public class Customer {
    private static final Random random = new Random();
    private int id;
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.id = random.nextInt(10);
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
