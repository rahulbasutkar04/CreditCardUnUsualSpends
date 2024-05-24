package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.domain.service.UnusualSpendDetector;
import com.amaap.creditcardunusualspends.domain.service.exception.IllegalAmountException;
import com.amaap.creditcardunusualspends.domain.service.impl.DefaultUnusualSpendDetector;
import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.DuplicateUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenditureServiceTest {

    private ExpenditureService expenditureService;
    private CreditCardRepository creditCardRepository;
    private TransactionRepository transactionRepository;
    private ExpenditureRepository expenditureRepository;
    private UnusualSpendAnalyser unusualSpendAnalyser;
    private UserService userService;
    private UserRepository userRepository;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        transactionRepository = injector.getInstance(TransactionRepository.class);
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        UnusualSpendDetector unusualSpendDetector = new DefaultUnusualSpendDetector(50);
        unusualSpendAnalyser = new UnusualSpendAnalyser(unusualSpendDetector);
        userRepository = injector.getInstance(UserRepository.class);
        userService = new UserService(userRepository);
        transactionService = new TransactionService(creditCardRepository, transactionRepository);
        expenditureService = new ExpenditureService(transactionRepository, creditCardRepository, expenditureRepository, unusualSpendAnalyser);
    }

    @Test
    void shouldBeAbleToReturnTrueIfTransactionsArePresentToGetTheUnusualSpends() throws InvalidUserIdException, InvalidUserNameException, InvalidEmailException, DuplicateUserIdException, IllegalAmountException {
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

        // act & assert
        assertTrue(expenditureService.getSpends());
    }

    @Test
    void shouldBeAbleToReturnFalseIfNoUnUsualDPendsFound() throws InvalidUserIdException, InvalidUserNameException, InvalidEmailException, DuplicateUserIdException, IllegalAmountException {
        // arrange
        userService.createUser(1, "RahulBasutkar", "abc@gmail.com");
        creditCardRepository.addCreditCardDetails(1, 12345678);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 12);
        Date previousMonthDate = calendar.getTime();

        calendar.set(2024, Calendar.MAY, 12);
        Date currentMonthDate = calendar.getTime();
        transactionService.performTransaction(currentMonthDate, Categories.GROCERY, 300.0);
        transactionService.performTransaction(previousMonthDate, Categories.GROCERY, 300.0);

        // act & assert
        assertFalse(expenditureService.getSpends());

    }

}
