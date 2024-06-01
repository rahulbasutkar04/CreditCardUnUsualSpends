package com.amaap.creditcardunusualspends;

import com.amaap.creditcardunusualspends.controller.CreditCardController;
import com.amaap.creditcardunusualspends.controller.CustomerController;
import com.amaap.creditcardunusualspends.controller.ExpenditureController;
import com.amaap.creditcardunusualspends.controller.TransactionController;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws InvalidDateException, InvaliCreditCardNumberException, InvalidCategoryException {

        Injector injector= Guice.createInjector(new CreditCardModule());
        CustomerController customerController=injector.getInstance(CustomerController.class);
        CreditCardController creditCardController=injector.getInstance(CreditCardController.class);
        TransactionController transactionController=injector.getInstance(TransactionController.class);
        ExpenditureController expenditureController=injector.getInstance(ExpenditureController.class);


        customerController.create("Rahul Basutkar","rahulbasutkar33@gmail.com");
        creditCardController.createCardFor(customerController.getUserId());
        transactionController.initialiseTransaction(creditCardController.getCreditCard(), "20-05-2024","Travel",200);
        transactionController.initialiseTransaction(creditCardController.getCreditCard(), "01-06-2024","Travel",1000);

        expenditureController.getUnusualSpendsFor(creditCardController.getCreditCard());



    }
}
