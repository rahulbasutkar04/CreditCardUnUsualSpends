package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.google.inject.Inject;

public class InMemoryUserRepository implements UserRepository {
    private final FakeDatabase fakeDatabase;

    @Inject
    public InMemoryUserRepository(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    @Override
    public void addUser(int id, String name, String email) {
        fakeDatabase.insertIntoUserTable(id, name, email);
    }
}
