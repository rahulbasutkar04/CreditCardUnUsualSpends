package com.amaap.creditcardunusualspends.domain.service;

import com.amaap.creditcardunusualspends.dto.UnusualSpendAlertDTO;

public class EmailService {

    public void sendUnusualSpendAlert(UnusualSpendAlertDTO unusualSpendAlertDTO) {
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("Unusual spending of :").append(unusualSpendAlertDTO.getTotalUnusualSpends()).append(" detected!\n\n");
        emailContent.append("Hello ").append(unusualSpendAlertDTO.getUserName()).append("!\n\n");
        emailContent.append("We have detected unusually high spending on your card in these categories:\n");

        unusualSpendAlertDTO.getUnusualSpends().forEach((category, amount) -> {
            emailContent.append("You spent:").append(amount).append(" on ").append(category).append("\n");
        });

        emailContent.append("\nThanks,\nThe RB`S Credit Card Company");

        sendEmail(unusualSpendAlertDTO.getUserEmail(), "Unusual Spending Alert", emailContent.toString());
    }
    private void sendEmail(String to, String subject, String body) {
        // Implement email sending logic
        System.out.println("Sending email to " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
