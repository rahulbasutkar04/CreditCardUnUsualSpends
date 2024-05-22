package com.amaap.creditcardunusualspends.module;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryUserRepository;
import com.amaap.creditcardunusualspends.repository.impl.db.FakeDatabase;
import com.amaap.creditcardunusualspends.repository.impl.db.impl.InMemoryFakeDatabase;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.UserService;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FakeDatabase.class).to(InMemoryFakeDatabase.class).in(Singleton.class);
        bind(UserRepository.class).to(InMemoryUserRepository.class).in(Singleton.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class).in(Singleton.class);
        bind(UserValidator.class).in(Singleton.class);
        bind(UserService.class).in(Singleton.class);
    }
}