package com.amaap.creditcardunusualspends.dto;

import java.util.Map;

public class UnusualSpendAlertDTO {
    private String userName;
    private String userEmail;
    private Map<String, Double> unusualSpends;
    private double totalUnusualSpends;

    public UnusualSpendAlertDTO(String userName, String userEmail, Map<String, Double> unusualSpends, double totalUnusualSpends) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.unusualSpends = unusualSpends;
        this.totalUnusualSpends = totalUnusualSpends;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Map<String, Double> getUnusualSpends() {
        return unusualSpends;
    }

    public double getTotalUnusualSpends() {
        return totalUnusualSpends;
    }
}
