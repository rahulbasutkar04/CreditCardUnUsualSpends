package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.CreditCard;
import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.module.CreditCardModule;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.service.dto.UnusualSpendNotificationDTO;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationBuilderTest {

    private CreditCardRepository creditCardRepository;
    private CustomerRepository customerRepository;
    private ExpenditureRepository expenditureRepository;
    private NotificationBuilder notificationBuilder;
    private CreditCard creditCard;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        Injector injector = Guice.createInjector(new CreditCardModule());
        creditCardRepository = injector.getInstance(CreditCardRepository.class);
        customerRepository = injector.getInstance(CustomerRepository.class);
        expenditureRepository = injector.getInstance(ExpenditureRepository.class);
        notificationBuilder = new NotificationBuilder(creditCardRepository, customerRepository, expenditureRepository);

        customer = new Customer("Rahul Basutkar", "rahul.basutkar@example.com");
        customerRepository.addCustomerData(customer);

        creditCard = new CreditCard(1);
        creditCard.setUserId(customer.getId());
        creditCardRepository.addCreditCardData(creditCard);
    }

    @Test
    void shouldBeAbleToBuildNotifierDataForValidCreditCardNumber() {
        // arrange
        List<Map<String, Object>> spendData = List.of(
                createSpendData("Travel", 100.0),
                createSpendData("Food", 50.0),
                createSpendData("Travel", 200.0)
        );
        expenditureRepository.addIntoExpenditureData(spendData);

        // act
        UnusualSpendNotificationDTO result = notificationBuilder.buildNotifierDataFor(creditCard.getCreditCardNumber());

        // assert
        assertEquals("Rahul Basutkar", result.getUserName());
        assertEquals("rahul.basutkar@example.com", result.getUserEmail());
        assertEquals(2, result.getSpendDetails().size());
        assertEquals(350.0, result.getTotalUnusualSpends());
    }

    @Test
    void shouldBeAbleToHandleEmptySpendData() {
        // act
        UnusualSpendNotificationDTO result = notificationBuilder.buildNotifierDataFor(creditCard.getCreditCardNumber());

        // assert
        assertEquals("Rahul Basutkar", result.getUserName());
        assertEquals("rahul.basutkar@example.com", result.getUserEmail());
        assertEquals(0, result.getSpendDetails().size());
        assertEquals(0.0, result.getTotalUnusualSpends());
    }

    @Test
    void shouldBeAbleToHandleNullSpendData() {
        // arrange
        addIntoExpenditureDataSafe(null);

        // act
        UnusualSpendNotificationDTO result = notificationBuilder.buildNotifierDataFor(creditCard.getCreditCardNumber());

        // assert
        assertEquals("Rahul Basutkar", result.getUserName());
        assertEquals("rahul.basutkar@example.com", result.getUserEmail());
        assertEquals(0, result.getSpendDetails().size());
        assertEquals(0.0, result.getTotalUnusualSpends());
    }

    @Test
    void shouldBeAbleToHandleDifferentDataFormats() {
        // arrange
        List<Map<String, Object>> spendData = List.of(
                createSpendData("Travel", "100.0"),
                createSpendData("Food", 50),
                createSpendData("Travel", 200.0),
                createSpendData("Misc", "invalid")
        );
        expenditureRepository.addIntoExpenditureData(spendData);

        // act
        UnusualSpendNotificationDTO result = notificationBuilder.buildNotifierDataFor(creditCard.getCreditCardNumber());

        // assert
        assertEquals("Rahul Basutkar", result.getUserName());
        assertEquals("rahul.basutkar@example.com", result.getUserEmail());
        assertEquals(3, result.getSpendDetails().size());
        assertEquals(350.0, result.getTotalUnusualSpends());
    }

    @Test
    void shouldReturnZeroForInvalidAmountData() {
        // arrange
        List<Map<String, Object>> spendData = List.of(
                createSpendData("Travel", new Object()),
                createSpendData("Food", "invalidString"),
                createSpendData("Misc", null)
        );
        expenditureRepository.addIntoExpenditureData(spendData);

        // act
        UnusualSpendNotificationDTO result = notificationBuilder.buildNotifierDataFor(creditCard.getCreditCardNumber());

        // assert
        assertEquals("Rahul Basutkar", result.getUserName());
        assertEquals("rahul.basutkar@example.com", result.getUserEmail());
        assertEquals(0.0, result.getTotalUnusualSpends());
    }

    private Map<String, Object> createSpendData(String category, Object extraAmount) {
        Map<String, Object> data = new HashMap<>();
        data.put("category", category);
        data.put("extraAmount", extraAmount);
        return data;
    }

    private void addIntoExpenditureDataSafe(List<Map<String, Object>> spendData) {
        if (spendData != null) {
            expenditureRepository.addIntoExpenditureData(spendData);
        }
    }
}
