package com.amaap.creditcardunusualspends.service.dto;

import java.util.List;

public class UnusualSpendNotificationDTO {
    private String userName;
    private String userEmail;
    private List<SpendDetail> spendDetails;
    private double totalUnusualSpends;

    public UnusualSpendNotificationDTO(String userName, String userEmail, List<SpendDetail> spendDetails, double totalUnusualSpends) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.spendDetails = spendDetails;
        this.totalUnusualSpends = totalUnusualSpends;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public List<SpendDetail> getSpendDetails() {
        return spendDetails;
    }

    public double getTotalUnusualSpends() {
        return totalUnusualSpends;
    }

    public static class SpendDetail {
        private String category;
        private double amount;

        public SpendDetail(String category, double amount) {
            this.category = category;
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }
    }

    @Override
    public String toString() {
        return "UnusualSpendNotificationDTO{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", spendDetails=" + spendDetails +
                ", totalUnusualSpends=" + totalUnusualSpends +
                '}';
    }
}
