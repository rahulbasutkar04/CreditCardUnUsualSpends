package com.amaap.creditcardunusualspends.module;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.Database;
import com.amaap.creditcardunusualspends.repository.impl.db.impl.InMemoryFakeDatabase;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CreditCardModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Database.class).to(InMemoryFakeDatabase.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class);
    }
}
