package com.amaap.creditcardunusualspends.service.validator;

public class UserValidator{


    public boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("^(?!.*(?:[^\\w\\s]|_))(?!\\s)(?:[a-zA-Z]{3,}(?:\\s[a-zA-Z]{3,})*?)$");
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            return false;
        }
        return true;
    }

}