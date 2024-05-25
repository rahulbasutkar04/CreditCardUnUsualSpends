package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendDetector;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.domain.service.impl.DefaultUnusualSpendDetector;
import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.ExpenditureService;
import com.amaap.creditcardunusualspends.service.TransactionService;
import com.amaap.creditcardunusualspends.service.UserService;
import com.amaap.creditcardunusualspends.service.exception.CreditCardException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenditureControllerTest {
    ExpenditureService expenditureService;
    CreditCardRepository creditCardRepository;
    TransactionService transactionService;
    TransactionRepository transactionRepository;
    UserRepository userRepository;
    UserService userService;
    ExpenditureRepository expenditureRepository;
    UnusualSpendAnalyser unusualSpendAnalyser;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionRepository = injector.getInstance(TransactionRepository.class);
        userRepository = injector.getInstance(UserRepository.class);
        transactionService = new TransactionService(creditCardRepository, transactionRepository);
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        UnusualSpendDetector unusualSpendDetector = new DefaultUnusualSpendDetector(50);
        unusualSpendAnalyser = new UnusualSpendAnalyser(unusualSpendDetector);
        expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, unusualSpendAnalyser);
        userService = new UserService(userRepository);

    }

    @Test
    void shouldBeAbleToRespondWithOkIfTransactionsArePresentToGetTheUnusualSpends() throws CreditCardException, IllegalAmountException {
        // arrange
        userService.createUser(1, "RahulBasutkar", "abc@gmail.com");
        creditCardRepository.addCreditCardDetails(1, 12345678);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 12);
        Date previousMonthDate = calendar.getTime();

        calendar.set(2024, Calendar.MAY, 12);
        Date currentMonthDate = calendar.getTime();

        transactionService.performTransaction(currentMonthDate, Categories.GROCERY, 300.0);
        transactionService.performTransaction(previousMonthDate, Categories.GROCERY, 200.0);

        ExpenditureController expenditureController = new ExpenditureController(expenditureService);
        Response expected = new Response(Http.OK, "Transactions are present..");

        // act
        Response actual = expenditureController.getSpends();

        // assert
        assertEquals(expected, actual);
    }
}
