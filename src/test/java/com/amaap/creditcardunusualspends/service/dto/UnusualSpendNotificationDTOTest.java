package com.amaap.creditcardunusualspends.service.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnusualSpendNotificationDTOTest {

    @Test
    void shouldBeAbleToInstantiateUnusualSpendNotificationDTO() {
        // arrange
        String userName = "Rahul Basutkar";
        String userEmail = "rahulbasutkar33@gmail.com";
        List<UnusualSpendNotificationDTO.SpendDetail> spendDetails = Arrays.asList(
                new UnusualSpendNotificationDTO.SpendDetail("Electronics", 500.0),
                new UnusualSpendNotificationDTO.SpendDetail("Travel", 1500.0)
        );
        double totalUnusualSpends = 2000.0;

        // act
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO(userName, userEmail, spendDetails, totalUnusualSpends);

        // assert
        assertNotNull(notificationDTO);
        assertEquals(userName, notificationDTO.getUserName());
        assertEquals(userEmail, notificationDTO.getUserEmail());
        assertEquals(spendDetails, notificationDTO.getSpendDetails());
        assertEquals(totalUnusualSpends, notificationDTO.getTotalUnusualSpends());
    }

    @Test
    void shouldBeAbleToReturnCorrectUserName() {
        // arrange
        String userName = "Rahul Basutkar";
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO(userName, "rahulbasutkar33@gmail.com", List.of(), 0.0);

        // act & assert
        assertEquals(userName, notificationDTO.getUserName());
    }

    @Test
    void shouldBeAbleToReturnCorrectUserEmail() {
        // arrange
        String userEmail = "rahulbasutkar33@gmail.com";
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO("Rahul Basutkar", userEmail, List.of(), 0.0);

        // act & assert
        assertEquals(userEmail, notificationDTO.getUserEmail());
    }

    @Test
    void shouldBeAbleToReturnCorrectSpendDetails() {
        // arrange
        List<UnusualSpendNotificationDTO.SpendDetail> spendDetails = Arrays.asList(
                new UnusualSpendNotificationDTO.SpendDetail("Electronics", 500.0),
                new UnusualSpendNotificationDTO.SpendDetail("Travel", 1500.0)
        );
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO("Rahul Basutkar", "rahulbasutkar33@gmail.com", spendDetails, 2000.0);

        // act & assert
        assertEquals(spendDetails, notificationDTO.getSpendDetails());
    }

    @Test
    void shouldBeAbleToReturnCorrectTotalUnusualSpends() {
        // arrange
        double totalUnusualSpends = 2000.0;
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO("Rahul Basutkar", "rahulbasutkar33@gmail.com", List.of(), totalUnusualSpends);

        // act & assert
        assertEquals(totalUnusualSpends, notificationDTO.getTotalUnusualSpends());
    }

    @Test
    void spendBeAbleToReturnCorrectCategoryAndAmount() {
        // arrange
        String category = "Electronics";
        double amount = 500.0;
        UnusualSpendNotificationDTO.SpendDetail spendDetail = new UnusualSpendNotificationDTO.SpendDetail(category, amount);

        // act & assert
        assertEquals(category, spendDetail.getCategory());
        assertEquals(amount, spendDetail.getAmount());
    }

    @Test
    void shouldBeAbleToGenerateConsistentToString() {
        // arrange
        String userName = "Rahul Basutkar";
        String userEmail = "rahulbasutkar33@gmail.com";
        List<UnusualSpendNotificationDTO.SpendDetail> spendDetails = Arrays.asList(
                new UnusualSpendNotificationDTO.SpendDetail("Electronics", 500.0),
                new UnusualSpendNotificationDTO.SpendDetail("Travel", 1500.0)
        );
        double totalUnusualSpends = 2000.0;
        UnusualSpendNotificationDTO notificationDTO = new UnusualSpendNotificationDTO(userName, userEmail, spendDetails, totalUnusualSpends);

        // act
        String expectedString = "UnusualSpendNotificationDTO{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", spendDetails=" + spendDetails +
                ", totalUnusualSpends=" + totalUnusualSpends +
                '}';

        // assert
        assertEquals(expectedString, notificationDTO.toString());
    }
}
