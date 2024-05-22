package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import java.util.HashMap;
import java.util.Map;

public class InMemoryFakeDatabase implements FakeDatabase {
    private final Map<Integer, Map<String, String>> userData = new HashMap<>();
    private  final Map<Integer,Long> creditCardData=new HashMap<>();
    private int lastInsertedId = -1;

    @Override
    public void insertIntoUserTable(int id, String name, String email) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userData.put(id, userDetails);
        lastInsertedId = id;
    }

    @Override
    public Map<Integer, Map<String, String>> getUserData() {
        return userData;
    }

    @Override
    public int getId() {
        return lastInsertedId;
    }

    @Override
    public void InsertIntoCreditCardTable(int id, long creditCardNumber) {
        creditCardData.put(id,creditCardNumber);
    }

    @Override
    public Map<Integer, Long> getCreditCardData() {
        return creditCardData;
    }

}
