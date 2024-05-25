package com.amaap.creditcardunusualspends;

import com.amaap.creditcardunusualspends.controller.*;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.EmailService;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.domain.service.impl.DefaultUnusualSpendDetector;
import com.amaap.creditcardunusualspends.module.AppModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.*;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws CreditCardException, IllegalAmountException {
        Injector injector = Guice.createInjector(new AppModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

         userController.createUser(1, "Rahul Basutkar", "rahulbasutlar33@Gmail.com");

        CreditCardRepository creditCardRepository = injector.getInstance(CreditCardRepository.class);
        CreditCardService creditCardService = new CreditCardService(userRepository, creditCardRepository);
        CreditCardController creditCardController = new CreditCardController(creditCardService);

        creditCardController.receiveCreditCardNumber(12345678);


        TransactionRepository transactionRepository = injector.getInstance(TransactionRepository.class);
        TransactionService transactionService = new TransactionService(creditCardRepository, transactionRepository);
        TransactionController transactionController = new TransactionController(transactionService);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 12);
        Date previousMonthDate = calendar.getTime();

        calendar.set(2024, Calendar.MAY, 12);
        Date currentMonthDate = calendar.getTime();

         transactionController.initialiseTransaction(currentMonthDate, Categories.GROCERY, 300.0);
         transactionController.initialiseTransaction(previousMonthDate, Categories.GROCERY, 200.0);


        ExpenditureRepository expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        UnusualSpendAnalyser unusualSpendAnalyser = new UnusualSpendAnalyser(new DefaultUnusualSpendDetector(50));
        ExpenditureService expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, unusualSpendAnalyser);
        ExpenditureController expenditureController = new ExpenditureController(expenditureService);
         expenditureController.getSpends();


        EmailService emailService = new EmailService();
        NotificationService notificationService = new NotificationService(userRepository, creditCardRepository, expenditureRepository, emailService);
        NotificationController notificationController = new NotificationController(notificationService);
        notificationController.notifyUnusualSpending(1);
    }
}
