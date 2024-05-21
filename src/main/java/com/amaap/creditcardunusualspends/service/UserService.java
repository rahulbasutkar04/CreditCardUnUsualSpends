package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;

public class UserService {

    UserValidator userValidator = new UserValidator();

    public boolean CreateUser(int userId, String name, String email) throws InvalidUserIdException, InvalidUserNameException, InvalidEmailException {
        if (userId <= 0) {
            throw new InvalidUserIdException("Invalid Id: " + userId);
        }

        if (!userValidator.isValidName(name)) {
            throw new InvalidUserNameException("Invalid Name: " + name);
        }

        if (!userValidator.isValidEmail(email)) {
            throw new InvalidEmailException("Invalid Email: " + email);
        }
        return true;
    }
}
