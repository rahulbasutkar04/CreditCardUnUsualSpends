package com.amaap.creditcardunusualspends.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UnusualSpendAlertDTOTest {
    private UnusualSpendAlertDTO alertDTO;
    private String userName;
    private String userEmail;
    private Map<String, Double> unusualSpends;
    private double totalUnusualSpends;

    @BeforeEach
    void setUp() {
        userName = "John Doe";
        userEmail = "john.doe@example.com";
        unusualSpends = new HashMap<>();
        unusualSpends.put("GROCERIES", 150.0);
        unusualSpends.put("TRAVEL", 300.0);
        totalUnusualSpends = 450.0;

        alertDTO = new UnusualSpendAlertDTO(userName, userEmail, unusualSpends, totalUnusualSpends);
    }

    @Test
    void shouldBeAbleToGetUserName() {
        assertEquals(userName, alertDTO.getUserName());
    }

    @Test
    void shouldBeAbleToGetUserEmail() {
        assertEquals(userEmail, alertDTO.getUserEmail());
    }

    @Test
    void shouldBeAbleToGetUnusualSpends() {
        assertEquals(unusualSpends, alertDTO.getUnusualSpends());
    }

    @Test
    void shouldBeAbleToGetTotalUnusualSpends() {
        assertEquals(totalUnusualSpends, alertDTO.getTotalUnusualSpends());
    }

}