package com.amaap.creditcardunusualspends.service.impl;

public class GoogleEmailSender {

    public boolean sendEmail(String to, String from, String subject, String text) {
        System.out.println("Mail sent to " + to + " From " + from);
        System.out.println(subject);
        System.out.println(text);
        return true;
    }

}
