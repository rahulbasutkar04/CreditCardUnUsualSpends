package com.amaap.creditcardunusualspends.module;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.amaap.creditcardunusualspends.repository.impl.db.impl.InMemoryFakeDatabase;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CreditCardModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Database.class).to(InMemoryFakeDatabase.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class).in(com.google.inject.Singleton.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class).in(com.google.inject.Singleton.class);
        bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(com.google.inject.Singleton.class);
        bind(ExpenditureRepository.class).to(InMemoryExpenditureRepository.class).in(com.google.inject.Singleton.class);
    }
}
