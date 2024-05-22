package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.service.Transaction;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.service.CreditCardService;
import com.amaap.creditcardunusualspends.service.TransactionService;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {
    TransactionService transactionService;
    CreditCardRepository creditCardRepository;
    Transaction transaction;
    CreditCardService creditCardService;
    @BeforeEach
    void setUp(){
        Injector injector = Guice.createInjector(new UserModule());
        creditCardRepository=injector.getInstance(CreditCardRepository.class);
        transaction=new Transaction();
        transactionService=new TransactionService(creditCardRepository, transaction);
        creditCardService=injector.getInstance(CreditCardService.class);
    }

    @Test
    void shouldBeAbleToInitializeTransactionForCreditCardNumber() throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
        // arrange
        TransactionController transactionController=new TransactionController(transactionService);
        CreditCardController creditCardController=new CreditCardController(creditCardService);
        creditCardController.receiveCreditCardNumber(12345678);
        Response expected=new Response(Http.OK,"Transaction Initialized..");

        // act
         Response actual=transactionController.performTransaction();

        // assert
        assertEquals(expected,actual);
    }
}
