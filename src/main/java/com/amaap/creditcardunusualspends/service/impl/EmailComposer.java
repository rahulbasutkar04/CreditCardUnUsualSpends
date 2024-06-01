package com.amaap.creditcardunusualspends.service.impl;

import com.amaap.creditcardunusualspends.service.NotifierService;
import com.amaap.creditcardunusualspends.service.dto.UnusualSpendNotificationDTO;

import java.util.List;

public class EmailComposer implements NotifierService {

    @Override
    public boolean sendNotification(String name, String email, List<UnusualSpendNotificationDTO.SpendDetail> spendDetails, double totalExpenditure) {

        GoogleEmailSender googleEmailSender = new GoogleEmailSender();
        String to = email;
        String from = "creditCardServices@gmail.com";
        String subject = "Unusual spending of :" + totalExpenditure + " detected!";

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Hello ").append(name).append(",\n\n");
        textBuilder.append("We have detected unusually high spending on your card in these categories:\n\n");

        for (UnusualSpendNotificationDTO.SpendDetail spendDetail : spendDetails) {
            textBuilder.append("* You spent ₹").append(spendDetail.getAmount())
                    .append(" on ").append(spendDetail.getCategory()).append("\n");
        }

        textBuilder.append("\nThanks,\n");
        textBuilder.append("The Credit Card Company");

        String text = textBuilder.toString();

        boolean b = googleEmailSender.sendEmail(to, from, subject, text);
        if (b) {
            System.out.println("Email is sent successfully");
        }

        return b;
    }
}
