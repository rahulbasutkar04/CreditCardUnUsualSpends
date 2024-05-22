package com.amaap.creditcardunusualspends.repository.impl.db;

import java.util.HashMap;
import java.util.Map;

public interface FakeDatabase {
    void insertIntoUserTable(int id,String name,String email);
    Map<Integer, Map<String, String>> getUserData();
    int getId();

    void InsertIntoCreditCardTable(int id,long CreditCardNumber);

    Map<Integer, Long> getCreditCardData();
}
