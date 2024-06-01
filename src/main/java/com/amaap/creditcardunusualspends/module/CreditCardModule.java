package com.amaap.creditcardunusualspends.module;

import com.amaap.creditcardunusualspends.controller.CreditCardController;
import com.amaap.creditcardunusualspends.controller.CustomerController;
import com.amaap.creditcardunusualspends.controller.ExpenditureController;
import com.amaap.creditcardunusualspends.controller.TransactionController;
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
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.CustomerService;
import com.amaap.creditcardunusualspends.service.ExpenditureService;
import com.amaap.creditcardunusualspends.service.TransactionService;
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



        bind(CustomerService.class).in(Singleton.class);
        bind(CreditCardService.class).in(Singleton.class);
        bind(TransactionService.class).in(Singleton.class);
        bind(ExpenditureService.class).in(Singleton.class);


        bind(CustomerController.class).in(Singleton.class);
        bind(CreditCardController.class).in(Singleton.class);
        bind(TransactionController.class).in(Singleton.class);
        bind(ExpenditureController.class).in(Singleton.class);
    }
}
