package com.amaap.creditcardunusualspends.repository.impl.db.impl;

import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import java.util.HashMap;
import java.util.Map;

public class InMemoryFakeDatabase implements FakeDatabase {
    private final Map<Integer, Map<String, String>> userData = new HashMap<>();

    @Override
    public void insertIntoUserTable(int id, String name, String email) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userData.put(id, userDetails);
    }

}
