package com.amaap.creditcardunusualspends.module;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryUserRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.amaap.creditcardunusualspends.repository.impl.db.impl.InMemoryFakeDatabase;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FakeDatabase.class).to(InMemoryFakeDatabase.class).in(Singleton.class);
        bind(UserRepository.class).to(InMemoryUserRepository.class).in(Singleton.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class).in(Singleton.class);
        bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(Singleton.class);
        bind(ExpenditureRepository.class).to(InMemoryExpenditureRepository.class).in(Singleton.class);
    }

}