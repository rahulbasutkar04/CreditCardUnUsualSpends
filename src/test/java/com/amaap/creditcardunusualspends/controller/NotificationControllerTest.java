package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendDetector;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.domain.service.impl.DefaultUnusualSpendDetector;
import com.amaap.creditcardunusualspends.dto.UnusualSpendAlertDTO;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NotificationControllerTest {
    private UserRepository userRepository;
    private CreditCardRepository creditCardRepository;
    private TransactionService transactionService;
    private ExpenditureService expenditureService;
    private NotificationService notificationService;
    private EmailService emailService;
    private TransactionRepository transactionRepository;
    private ExpenditureRepository expenditureRepository;
    private UnusualSpendAnalyser unusualSpendAnalyser;
    private UnusualSpendDetector unusualSpendDetector;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        userRepository = injector.getInstance(UserRepository.class);
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionRepository = injector.getInstance(TransactionRepository.class);
        unusualSpendDetector = new DefaultUnusualSpendDetector(50);
        unusualSpendAnalyser = new UnusualSpendAnalyser(unusualSpendDetector);
        transactionService = new TransactionService(creditCardRepository, transactionRepository);

        expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, unusualSpendAnalyser);
        emailService = new EmailService();

        notificationService = new NotificationService(userRepository, creditCardRepository, expenditureRepository, emailService);
    }


    @Test
    void shouldBeAbleToRespondWithOkWhenMailIsSent() throws IllegalAmountException {
        // arrange
        int userId = 1;
        String userName = "Rahul Basutkar";
        String userEmail = "rahul@example.com";
        userRepository.addUser(userId, userName, userEmail);
        long creditCardNumber = 12345678L;
        creditCardRepository.addCreditCardDetails(userId, creditCardNumber);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 12);
        Date previousMonthDate = calendar.getTime();

        calendar.set(2024, Calendar.MAY, 12);
        Date currentMonthDate = calendar.getTime();

        transactionService.performTransaction(previousMonthDate, Categories.GROCERY, 50.0);
        transactionService.performTransaction(currentMonthDate, Categories.GROCERY, 148.0);
        expenditureService.getSpends();
        NotificationController notificationController=new NotificationController(notificationService);
        Response expected=new Response(Http.OK,"Notification sent successfully.");

        // act
        Response actual=notificationController.notifyUnusualSpending(userId);

        // assert
        assertEquals(expected,actual);

    }



}